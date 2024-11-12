document.addEventListener('DOMContentLoaded', () => {
    loadAvailableRoutes();
    setupRegistration();
    setupLogin();
});

function loadAvailableRoutes() {
    // Fetching bus routes based on user input
    const from = "CityA"; // Replace with user input
    const to = "CityB"; // Replace with user input

    axios.get(`/api/bus/available-routes?from=${from}&to=${to}`)
        .then(response => {
            displayRoutes(response.data);
        })
        .catch(error => {
            console.error('Error fetching bus routes:', error);
        });
}

function displayRoutes(routes) {
    const routesDiv = document.getElementById('bus-routes');
    routesDiv.innerHTML = "<h2>Available Routes</h2>";

    routes.forEach(route => {
        const routeDiv = document.createElement('div');
        routeDiv.innerHTML = `
            <p>From: ${route.fromLocation} To: ${route.toLocation}</p>
            <p>Departure: ${route.departureTime}, Arrival: ${route.arrivalTime}, Price: $${route.price}</p>
            <button onclick="openBookingForm(${route.id})">Book Now</button>
        `;
        routesDiv.appendChild(routeDiv);
    });
}

function openBookingForm(busId) {
    const bookingForm = document.getElementById('booking-form');
    bookingForm.innerHTML = `
        <h3>Booking Form</h3>
        <form id="bookingForm">
            <input type="number" id="seats" placeholder="Number of Seats" required>
            <button type="submit">Book Now</button>
        </form>
    `;

    document.getElementById('bookingForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const seats = document.getElementById('seats').value;

        axios.post('/api/bookings', { busId: busId, numberOfSeats: seats })
            .then(response => {
                alert('Booking successful! Your e-ticket will be sent via email.');
            })
            .catch(error => {
                console.error('Booking failed!', error);
            });
    });
}

function setupRegistration() {
    const registrationDiv = document.getElementById('registration');
    registrationDiv.innerHTML = `
        <h2>Register</h2>
        <form id="registrationForm">
            <input type="text" id="name" placeholder="Name" required>
            <input type="email" id="email" placeholder="Email" required>
            <input type="password" id="password" placeholder="Password" required>
            <button type="submit">Register</button>
        </form>
    `;

    document.getElementById('registrationForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        axios.post('/api/auth/register', { name, email, password })
            .then(response => {
                alert('Registration successful!');
            })
            .catch(error => {
                console.error('There was an error registering!', error);
            });
    });
}

function setupLogin() {
    const loginDiv = document.getElementById('login');
    loginDiv.innerHTML = `
        <h2>Login</h2>
        <form id="loginForm">
            <input type="email" id="loginEmail" placeholder="Email" required>
            <input type="password" id="loginPassword" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>
    `;

    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;

        axios.post('/api/auth/login', { email, password })
            .then(response => {
                localStorage.setItem('token', response.data.token);
                alert('Login successful!');
            })
            .catch(error => {
                console.error('Login failed!', error);
            });
    });
}
