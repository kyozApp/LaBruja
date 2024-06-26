options = {
    "cursorOuter": "circle",
    "hoverEffect": "circle-move",
    "hoverItemMove": false,
    "defaultCursor": false,
    "outerWidth": 40,
    "outerHeight": 40
};
magicMouse(options);

let circleMouse = document.getElementById("magicMouseCursor");
let circlePointer = document.getElementById("magicPointer");

document.addEventListener("mousemove", () => {
    circleMouse.style.display = "block";
    circlePointer.style.display = "block";
});