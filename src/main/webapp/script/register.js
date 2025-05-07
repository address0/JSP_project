document.addEventListener('DOMContentLoaded', function() {
    const emailInput = document.getElementById('email');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirm-password');
    const telInput = document.getElementById('tel');
    const submitButton = document.querySelector('.login-button');

    const inputs = [emailInput, usernameInput, passwordInput, confirmPasswordInput, telInput];

    validateOnInput(emailInput, validateEmail);
    validateOnInput(usernameInput, validateUsername);
    validateOnInput(passwordInput, validatePassword);
    validateOnInput(confirmPasswordInput, () => validateConfirmPassword(passwordInput, confirmPasswordInput));
    validateOnInput(telInput, validateTel);

    function validateOnInput(input, validateFn) {
        const message = input.nextElementSibling;
        console.log('submitButton:', submitButton);

        input.addEventListener('input', function() {
            if (validateFn(input.value)) {
                input.classList.add('valid');
                input.classList.remove('invalid');
                message.classList.remove('show');
            } else {
                input.classList.add('invalid');
                input.classList.remove('valid');
                message.classList.add('show');
            }
            updateSubmitButtonState();
        });

        input.addEventListener('blur', function() {
            if (validateFn(input.value)) {
                input.classList.remove('valid');
                input.classList.remove('invalid');
                input.classList.add('success');
            } else {
                input.classList.remove('success');
            }
            updateSubmitButtonState();
        });

        input.addEventListener('focus', function() {
            input.classList.remove('success');
        });
    }

    function validateEmail(value) {
        const emailRegex = /^[a-zA-Z0-9._%+-]{5,15}@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return emailRegex.test(value);
    }

    function validateUsername(value) {
        return value.trim().length > 0;
    }

    function validatePassword(value) {
        const pwRegex = /^(?=.*[a-zA-Z])(?=.*\d)[A-Za-z\d]{5,15}$/;
        return pwRegex.test(value);
    }

    function validateConfirmPassword(passwordInput, confirmInput) {
        return passwordInput.value === confirmInput.value && passwordInput.value !== '';
    }

    function validateTel(value) {
        const telRegex = /^\d{11}$/;
        return telRegex.test(value);
    }

    function updateSubmitButtonState() {
        const allValid = inputs.every(input => input.classList.contains('success'));
        submitButton.disabled = !allValid;
    }

    updateSubmitButtonState();
});