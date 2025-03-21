function initMap(eventId, latitude, longitude) {
    console.log(`Initializing map for event ID: ${eventId}, Latitude: ${latitude}, Longitude: ${longitude}`);
    ymaps.ready(function () {
        var map = new ymaps.Map(`map-${eventId}`, {
            center: [latitude, longitude],
            zoom: 10,
            controls: ['zoomControl', 'fullscreenControl']
        });
        var placemark = new ymaps.Placemark([latitude, longitude]);
        map.geoObjects.add(placemark);
    });
}
