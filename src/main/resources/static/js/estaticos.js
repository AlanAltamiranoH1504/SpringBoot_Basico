document.addEventListener("DOMContentLoaded", () => {
    const form_producto = document.querySelector("#form_producto");
    form_producto.addEventListener("submit", submitForm);

    function submitForm(e) {
        e.preventDefault();
        const nombre = document.querySelector("#nombre").value;
        const descripcion = document.querySelector("#descripcion").value;
        const precio = document.querySelector("#precio").value;

        if (nombre.trim() === "" || nombre == null) {
            console.log("El nombre del producto esta vacio");
            return;
        }
        if (descripcion.trim() === "" || descripcion == null) {
            console.log("La descripcion del producto esta vacia");
            return;
        }
        if (precio.trim() === "" || precio.trim() == null) {
            console.log("El precio del producto esta vacio");
            return;
        }

        const producto = {
          nombre,
          descripcion,
          precio
        };

        saveProducto(producto);
        // console.log(producto)
    }

    function saveProducto(producto) {
        fetch("/productos/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(producto)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            console.log(data);
        }).catch((error) => {
            console.log("Error en la peticion");
            console.log(error.message);
        })
    }
});