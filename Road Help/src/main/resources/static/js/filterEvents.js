function getLocationAndFilterEvents() {
    //Проверка поддержки геолокации
    if (navigator.geolocation) {
        //Получение местоположение пользователя
        navigator.geolocation.getCurrentPosition(
            function(position) {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;

                const csrfToken = document.querySelector('meta[name="_csrf"]').content;
                const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

                // AJAX-запрос для фильтрации событий по геопозиции
                const xhr = new XMLHttpRequest();
                xhr.open("POST", "/event/filter", true);
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(csrfHeader, csrfToken);
                //Обработка состояний запроса
                xhr.onreadystatechange = function() { //Срабатывает каждый раз, когда меняется readystate
                    if (xhr.readyState === 4) { //4 = запрос завершен и готов ответ
                        if (xhr.status === 200) {
                            const events = JSON.parse(xhr.responseText);
                            console.log("Filtered events received:", events);
                            updateEvents(events, csrfToken);
                        } else {
                            const errorResponse = JSON.parse(xhr.responseText);
                            console.error("Failed to fetch events: " + xhr.status + ", " + errorResponse.message);
                            showError("An error occurred while fetching events. Please try again later.");
                        }
                    }
                };
                xhr.send(JSON.stringify({ latitude: latitude, longitude: longitude })); //Отправляем на сервер
            },
            //Обработка ошибок получения геопозиции
            function(error) {
                let errorMessage = '';
                switch(error.code) {
                    case error.PERMISSION_DENIED:
                        errorMessage = "Please enable geolocation. Our service does not work without it.";
                        break;
                    case error.POSITION_UNAVAILABLE:
                        errorMessage = "Location information is unavailable. Please try again later.";
                        break;
                    case error.TIMEOUT:
                        errorMessage = "The request to get your location timed out. Please try again.";
                        break;
                    case error.UNKNOWN_ERROR:
                        errorMessage = "An unknown error occurred. Please try again.";
                        break;
                }
                showError(errorMessage);
            }
        );
    } else {
        showError("Geolocation is not supported by this browser.");
    }
}
function showError(message) {
    const eventsContainer = document.getElementById('eventsContainer');
    eventsContainer.innerHTML = `<p class="text-center text-danger">${message}</p>`;
}

//Обновление списка событий на странице
function updateEvents(events, csrfToken) {
    const eventsContainer = document.getElementById('eventsContainer');
    eventsContainer.innerHTML = ''; // Очистка старых событий

    if (events.length === 0) {
        eventsContainer.innerHTML = '<p class="text-center">Seems like no one in your town needs help.</p>';
        return;
    }
    //Фильтрация завершенных событий
    const filteredEvents = events.filter(event => event.status !== "COMPLETED");
    //Создание карточек событий и добавление на страницу
    filteredEvents.forEach(event => {
        const eventCard = document.createElement('div');
        eventCard.className = 'col-md-6 mb-4';
        console.log("Event Data:", event);
        eventCard.innerHTML = `
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">${event.title}</h2>
                    <p class="card-text"><strong>Description:</strong> ${event.description}</p>
                    <p class="card-text"><strong>Contact Info:</strong> ${event.contactInfo}</p>
                    <p class="card-text"><strong>Created At:</strong> ${event.createdAt}</p>
                    <p class="card-text"><strong>Creator Name:</strong> <a href="/profile/${event.creatorId}">${event.creatorName}</a></p>
                    <p class="card-text"><strong>Status:</strong> ${event.status}</p>
                    ${event.status === "FREE" && !event.currentUserCreator ? `
                        <form action="/reply/respond" method="post">
                            <input type="hidden" name="_csrf" value="${csrfToken}" />
                            <input type="hidden" name="eventId" value="${event.id}">
                            <button type="submit" class="btn btn-primary">Respond</button>
                        </form>
                    ` : event.status === "IN_PROGRESS" ? `
                        ${event.responderName && event.responderId ? `
                            <p class="card-text"><strong>Responder Name:</strong> <a href="/profile/${event.responderId}">${event.responderName}</a></p>
                        ` : ''}
                        ${event.responseCreatedAt ? `<p class="card-text"><strong>Response Created At:</strong> ${event.responseCreatedAt}</p>` : ''}
                    ` : ''}
                </div>
                <div class="card-footer">
                    <div id="map-${event.id}" class="map"></div>
                </div>
            </div>
        `;
        eventsContainer.appendChild(eventCard);
        initMap(event.id, event.latitude, event.longitude);
    });
}
