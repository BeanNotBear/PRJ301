let lower;
let upper;
let lower_range;
let upper_range;

function getPrice(price) {
    lower = price[0];
    upper = price[1];
    filter();
}

function filter() {
//    const url = contextPath + '/admin/category?action=add&categoryName=' + addedCategoryName;
    var url = contextPath + '/category?action=filter';
    var sizeIdList = [];
    var categoryIdList = [];

    var sizes = document.getElementsByClassName('size');
    var numberOfSizes = sizes.length;

    var categorys = document.getElementsByClassName('category');
    var numberOfCategorys = categorys.length;

    var sortType = document.getElementById('sort').value;
    console.log(sortType);

    for (let i = 0; i < numberOfCategorys; i++) {
        if (categorys[i].checked) {
            categoryIdList.push(categorys[i].id);
        }
    }

    for (let i = 0; i < numberOfSizes; i++) {
        if (sizes[i].checked) {
            sizeIdList.push(sizes[i].id);
        }
    }

    var formData = {
        categoryIds: categoryIdList,
        sizeIds: sizeIdList,
        lowerPrice: lower,
        upperPrice: upper,
        sortType: sortType,
        lower_range: lower_range,
        upper_range: upper_range
    };

    console.log(formData);

    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        success: function (data) {
            var content = document.getElementById('content');
            content.innerHTML = data;
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}

function changePage(page) {
    var pageNumber = page.textContent;
    var pages = document.getElementsByClassName('page');
    for (let i = 0; i < pages.length; i++) {
        pages[i].classList.remove('active');
    }
    page.classList.add('active');
    
    upper_range = parseInt(pageNumber) + 5 * parseInt(pageNumber);
    if(pageNumber === 1) {
        lower_range = 0;
    } else {
        lower_range = upper_range - 6;
    }
    console.log("lower " + lower_range);
    console.log("upper " + upper_range);
    
    filter();
}

// Function to handle next page
function nextPage() {
    var lastPage = parseInt(document.getElementById('last-page').textContent);
    for (let i = lastPage; i < lastPage + 3; i++) {
        document.getElementsByClassName('page')[i - lastPage].innerHTML = i;
        document.getElementsByClassName('page')[i - lastPage].classList.remove('active');
    }
}

// Function to handle previous page
function previousPage() {
    var firstPage = parseInt(document.getElementById('first-page').textContent);
    if (firstPage >= 3) {
        for (let i = 0; i < 3; i++) {
            console.log(i);
            document.getElementsByClassName('page')[i].innerHTML = firstPage + i - 2;
            document.getElementsByClassName('page')[i].classList.remove('active');
        }
    }
}