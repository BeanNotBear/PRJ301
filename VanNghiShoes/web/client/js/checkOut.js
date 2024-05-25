function checkOut() {
    var methodPay = document.getElementsByName('selector');
    var  method;
    for(let i = 0; i < methodPay.length; i++) {
        if(methodPay[i].checked) {
            document.getElementById('payment').value = methodPay[i].value;
            method = methodPay[i].value;
            console.log(document.getElementById('payment').value);
        }
    }
    console.log(methodPay);
    var amount;
    if(method === 'VN PAY') {
        amount = parseInt(document.getElementById('total').textContent.split('V')[0]);
        console.log(amount);
        document.getElementById('amount').value = amount ;
    }
    document.getElementById('checkOutForm').submit();
}