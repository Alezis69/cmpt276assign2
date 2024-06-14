function goBack() {
    windows.location.href = "/Rectangle.html";
}
document.getElementById("rectangleForm").addEventListener("submit", function(event) {
    event.preventDefault();
    addRectangle();
});
function validateValues() {
    const name = document.getElementById("name").value;
    const width = document.getElementById("width").value;
    const height = document.getElementById("height").value;
    const colour = document.getElementById("colour").value;
    const borderColour = document.getElementById("borderColour").value;
    const borderStyle = document.getElementById("borderStyle").value;

    if (!name || !width || !height || !colour || !borderColour || !borderStyle) {
        alert("Not All Fields Are Filled");
        return false;
    }
    return true;
}
function addRectangle() {
    if (!validateValues()) {
        return;
    }

}