async function initMap() {
    await ymaps.ready;

    const map = new ymaps.Map("map", {
        center: [55.76, 37.64],
        zoom: 10
    });

    let myPlacemark;

    map.events.add('click', function (e) {
        const coords = e.get('coords');

        if (myPlacemark) {
            myPlacemark.geometry.setCoordinates(coords);
        } else {
            myPlacemark = new ymaps.Placemark(coords, {}, {});
            map.geoObjects.add(myPlacemark);
        }

        document.getElementById('latitude').value = coords[0];
        document.getElementById('longitude').value = coords[1];

        document.getElementById('mapError').style.display = 'none';
    });
}

function validateForm() {
    const latitude = document.getElementById('latitude').value;
    const longitude = document.getElementById('longitude').value;

    if (!latitude || !longitude) {
        document.getElementById('mapError').style.display = 'block';
        return false;
    }

    return true;
}

ymaps.ready(initMap);
