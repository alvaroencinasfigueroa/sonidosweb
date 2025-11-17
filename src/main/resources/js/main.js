// Modal Functions
function openLoginModal() {
    document.getElementById('loginModal').classList.add('active');
    document.body.style.overflow = 'hidden';
}

function closeLoginModal() {
    document.getElementById('loginModal').classList.remove('active');
    document.body.style.overflow = 'auto';
}

function openRegisterModal() {
    document.getElementById('registerModal').classList.add('active');
    document.body.style.overflow = 'hidden';
}

function closeRegisterModal() {
    document.getElementById('registerModal').classList.remove('active');
    document.body.style.overflow = 'auto';
}

function switchToRegister() {
    closeLoginModal();
    openRegisterModal();
    return false;
}

function switchToLogin() {
    closeRegisterModal();
    openLoginModal();
    return false;
}

// Close modal when clicking outside
window.onclick = function(event) {
    const loginModal = document.getElementById('loginModal');
    const registerModal = document.getElementById('registerModal');

    if (event.target === loginModal) {
        closeLoginModal();
    }
    if (event.target === registerModal) {
        closeRegisterModal();
    }
}

// Close modal with ESC key
document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        closeLoginModal();
        closeRegisterModal();
    }
});

// Smooth scroll
function scrollToFeatures() {
    document.getElementById('features').scrollIntoView({
        behavior: 'smooth'
    });
}

// Show login required alert
function showLoginRequired() {
    alert('Por favor, inicia sesión o regístrate para descargar las aplicaciones.');
    openLoginModal();
}

// Login Form Handler
document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    try {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, password })
        });

        if (response.ok) {
            const data = await response.json();
            alert('¡Inicio de sesión exitoso!');
            closeLoginModal();
            // Redirect to dashboard or reload page
            window.location.reload();
        } else {
            const error = await response.json();
            alert('Error: ' + (error.message || 'Credenciales inválidas'));
        }
    } catch (error) {
        alert('Error al iniciar sesión. Por favor, intenta de nuevo.');
        console.error('Error:', error);
    }
});

// Register Form Handler
document.getElementById('registerForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const name = document.getElementById('registerName').value;
    const email = document.getElementById('registerEmail').value;
    const password = document.getElementById('registerPassword').value;
    const confirmPassword = document.getElementById('registerConfirmPassword').value;

    // Validate passwords match
    if (password !== confirmPassword) {
        alert('Las contraseñas no coinciden');
        return;
    }

    // Validate password length
    if (password.length < 8) {
        alert('La contraseña debe tener al menos 8 caracteres');
        return;
    }

    try {
        const response = await fetch('/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, email, password })
        });

        if (response.ok) {
            const data = await response.json();
            alert('¡Registro exitoso! Por favor, inicia sesión.');
            closeRegisterModal();
            openLoginModal();
        } else {
            const error = await response.json();
            alert('Error: ' + (error.message || 'No se pudo completar el registro'));
        }
    } catch (error) {
        alert('Error al registrarse. Por favor, intenta de nuevo.');
        console.error('Error:', error);
    }
});