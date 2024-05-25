var flag = false;
let sizeId = 0;

function checkEmail(element) {
    const pattern = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    // Get the value of the input field
    var value = element.value;

    // Get the message
    var msg = document.querySelector('#email_err');
    console.log(msg);

    console.log(element);

    // Test if the value matches the regex pattern
    if (value.match(pattern)) {
        msg.style.display = 'none';
        element.style.borderBottomColor = '#059862';
        console.log("Input matches the regex pattern");
        flag = true;
        return true;
    } else {
        msg.innerHTML = 'The email is not incorrect format!';
        msg.style.display = 'block';
        element.style.borderBottomColor = 'red';
        console.log(pattern);
        console.log("Input does not match the regex pattern");
        flag = false;
        return false;
    }
}

function checkFullname(element) {
    const pattern = /^[a-zA-Z\s]+$/;
    var value = element.value;


    var msg = document.querySelector('#fullname_err');
    if (value.trim().match(pattern)) {
        msg.style.display = 'none';
        element.style.borderBottomColor = '#059862';
        console.log("Input matches the regex pattern");
        flag = true;
        return true;
    } else {
        msg.innerHTML = 'Full name is requierd';
        msg.style.display = 'block';
        element.style.borderBottomColor = 'red';
        console.log("Input does not match the regex pattern");
        flag = false;
        return false;
    }
}

function checkPhoneNumber(element) {
    const pattern = /^\+?(\d{1,3})?[- .]?\(?(\d{3})\)?[- .]?(\d{3})[- .]?(\d{4})$/;

    var value = element.value;

    var msg = document.querySelector('#phone_err');

    if (value.match(pattern)) {
        msg.style.display = 'none';
        element.style.borderBottomColor = '#059862';
        console.log("Input matches the regex pattern");
        flag = true;
        return true;
    } else {
        msg.innerHTML = 'Phone number must be number and have 10 character';
        msg.style.display = 'block';
        element.style.borderBottomColor = 'red';
        console.log("Input does not match the regex pattern");
        falg = false;
        return false;
    }
}

function checkPassword(element) {
    const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    var value = element.value;

    var rePassword = document.querySelector('#repassword');
    console.log(value);

    var msg = document.querySelector('#password_err');
    var msg2 = document.querySelector('#repassword_err');

    if (value.match(pattern)) {
        msg.style.display = 'none';
        msg2.style.display = 'none';
        element.style.borderBottomColor = '#059862';
        rePassword.style.borderBottomColor = '#059862';
        console.log("Input matches the regex pattern pass");
        rePassword.style.display = 'block';
        flag = true;
        return true;
    } else {
        msg.innerHTML = 'Password must contain at least one lowercase letter, one uppercase letter, one digit, one special character, and be at least 8 characters long.';
        msg2.innerHTML = 'Password must contain at least one lowercase letter, one uppercase letter, one digit, one special character, and be at least 8 characters long.';
        msg.style.display = 'block';
        msg2.style.display = 'block';
        element.style.borderBottomColor = 'red';
        console.log("Input does not match the regex pattern");
//        rePassword.style.display = 'none';
        flag = false;
        return false;
    }
}

function checkRepassword(element) {
    var password = document.querySelector('#password');
    if (checkPassword(password)) {
        var rePassword = document.querySelector('#repassword');

        var passMsg = document.querySelector('#password_err');
        var repassMsg = document.querySelector('#repassword_err');
        console.log(password);
        console.log(rePassword);

        if (password.value === rePassword.value) {
            passMsg.style.display = 'none';
            repassMsg.style.display = 'none';
            password.style.borderBottomColor = '#059862';
            rePassword.style.borderBottomColor = '#059862';
            flag = true;
            return true;
        } else {
            var msg = 'Password and Re-Password do not match';
            passMsg.innerHTML = msg;
            repassMsg.innerHTML = msg;
            password.style.borderBottomColor = 'red';
            rePassword.style.borderBottomColor = 'red';
            passMsg.style.display = 'block';
            repassMsg.style.display = 'block';
        }
    }
    flag = false;
    return false;
}

function submitFormRegister(element) {
    var email = document.querySelector('#email').value;
    var fullName = document.querySelector('#fullname').value;
    var phoneNumber = document.querySelector('#phone').value;
    var password = document.querySelector('#password').value;
    var repassword = document.querySelector('#repassword').value;
    var validation = email.length !== 0 && fullName.length !== 0
            && phoneNumber.length !== 0 && password.length !== 0
            && repassword.length !== 0;
//    console.log(validation);
//    console.log(flag);
    if (flag && validation) {
        element.style.cursor = 'pointer';
        element.disabled = false;
        console.log(element.disabled);
    } else {
        element.style.cursor = 'not-allowed';
        element.disabled = true;
    }
}

function submitFormchangePasswordForget(element) {
    var password = document.querySelector('#password').value;
    var repassword = document.querySelector('#repassword').value;
    var validation = password.length !== 0 && repassword.length !== 0;

    if (flag && validation) {
        element.style.cursor = 'pointer';
        element.disabled = false;
        console.log(element.disabled);
    } else {
        element.style.cursor = 'not-allowed';
        element.disabled = true;
    }
}

function checkSelectSize(flag) {
    var element = document.getElementById('addCart');
    if (!flag) {
        element.style.cursor = 'not-allowed';
        element.disabled = true;
        return false;
    } else {
        element.style.cursor = 'pointer';
        element.disabled = false;
        return true;
    }
}

function checkOption(quantity) {
    var sizes = document.getElementsByName('size');
    var length = sizes.length;
    var sst = document.getElementById('sst');
    var qty = document.getElementById('qty');
    var flag = false;
    
    for (var i = 0; i < length; i++) {
        if (sizes[i].checked) {
            sizeId = sizes[i].value;
            sst.max = quantity;
            sst.value = 1;
            qty.style.display = 'block';
            flag = true;
        }
    }
    checkSelectSize(flag);
}

function checkQuantity() {
    element = document.getElementById('sst');
    quantity = parseInt(element.value);
//    if (isNaN(quantity)) {
//        element.value = 1;
//        return;
//    }
    min = parseInt(element.min);
    max = parseInt(element.max);
    console.log(quantity);
    console.log(min);
    console.log(max);
    if (quantity < min || quantity > max) {
        if (quantity < min) {
            element.value = 1;
            return;
        }
        if (quantity > max) {
            element.value = max;
            return;
        }
    }
}

var seconds = 60;
var timer;

function startCountdown() {
    timer = setInterval(updateCountdown, 1000);
}

function updateCountdown() {
    seconds--;
    document.getElementById("timer").innerText = seconds;
    if (seconds <= 0) {
        clearInterval(timer);
        document.getElementById("resendLink").innerText = "Send again";
        document.getElementById("countdown").innerText = "";
    }
}

