
const selectX = document.getElementById("choose-form:X")
const yText = document.querySelector('input[type="text"]');
const arrayR = ["1", "1.5", "2", "2.5", "3", "3.5", "4"];
const arrayX = ["-4","-3", "-2","-1", "0","1","2","3","4"];
let circles = [];
let pointId = 0;
let start = 150;
let rPosition = 100;
const xErrorElement = document.getElementById("xError");
const submit = document.getElementById("choose-form:submit-button");
const yErrorElement = document.getElementById("yError");
const rErrorElement = document.getElementById("rError");
const rHidden = document.querySelectorAll('.value-button__input')[0];
let showR = document.getElementById("show-r-value");
let slider = document.getElementById("choose-form:r-value_hidden");
const points = new Map();
let svg = document.getElementById("svg");
yText.addEventListener('input', function(e){
    let inputValue = e.target.value;
    e.target.value = inputValue.replace(/[^0-9.,\-]/g, '');
});

submit.addEventListener('click', ()=>{
    yText.value = yText.value.replace(/[,]/g,'.');
})

function setX(e) {
    document.querySelectorAll('.p1 button').forEach(function (element) {
        element.className = '';
    })
    e.className = 'selected'
    document.getElementById('choose-form:X_hinput').value = e.childNodes[0].textContent
}
function updateR(){
    showR.innerHTML = slider.value;
    drawPointsByR(slider.value);
}
function removeCircles(){
    for(let i = 0; i < circles.length; i++){
        svg.removeChild(circles[i]);
    }
    circles = [];
}
function drawPointsByR(r){
    removeCircles();
    points.forEach((v,k) =>{
        drawPoint(v.x, v.y, r, v.result);
    })


}
function resultSet(x,y,r, result){
    result = result.toString();
    points.set('point' + pointId, {x, y, r, result});
    pointId+=1;
    drawPoint(x, y, r, result);
}
function drawPoint(x, y, r, result){
    [x, y] = untransformed(x, y, r);
    let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("cx", x);
    circle.setAttribute("cy", y);
    circle.setAttribute("r", "5");
    if (result === "true")
        circle.style.fill = "#09a53d";
    else
        circle.style.fill = "#cdc684";
    circles.push(circle);
    svg.appendChild(circle);
}

svg.addEventListener("click", (event) => {
    if (arrayR.includes(slider.value)){
        const  rect = svg.getBoundingClientRect();
        let x = event.clientX - rect.left;
        let y = event.clientY - rect.top;
        let coord = transform(x, y, slider.value);
        x = coord[0];
        y = coord[1];
        document.getElementById("graphSelect:graph-x").value = x;
        document.getElementById("graphSelect:graph-y").value = y;
        updateBeanValues();

    }
    else {
        document.getElementById("choose-form:r-select").style.border = '3px solid red';
    }
})
function transform(x, y, r){
    let scale = r;
    x = (x - start)/rPosition * scale;
    y = -(y - start)/rPosition * scale;
    return [x, y];
}
function untransformed(x, y, r){
    let scale = r;
    x = x * rPosition / scale + start;
    y = -y * rPosition / scale + start;
    return [x, y];
}

function clear(){
    points.clear();
    circles = [];
    pointId = 0;
    removeCircles();
}