function initMap(latitude, longitude) {
    var map = new ymaps.Map("map", {
        center: [latitude, longitude],
        zoom: 10
    });

    var myPlacemark = new ymaps.Placemark([latitude, longitude], {}, {
        draggable: true
    });
    map.geoObjects.add(myPlacemark);
    document.getElementById('latitude').value = latitude.toString().replace(',', '.');
    document.getElementById('longitude').value = longitude.toString().replace(',', '.');

    map.events.add('click', function (e) {
        var coords = e.get('coords');
        myPlacemark.geometry.setCoordinates(coords);
        document.getElementById('latitude').value = coords[0].toString().replace(',', '.');
        document.getElementById('longitude').value = coords[1].toString().replace(',', '.');
    });
}

function validateForm() {
    var latitude = document.getElementById('latitude').value;
    var longitude = document.getElementById('longitude').value;

    if (!latitude || !longitude) {
        document.getElementById('mapError').style.display = 'block';
        return false; // Prevent form submission
    }

    return true;
}