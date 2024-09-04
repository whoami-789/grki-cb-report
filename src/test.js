fetch('http://grki-service/grci/resources/cb')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('JSON response:', data);
        console.log(data)
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
        console.log(error)
    });


