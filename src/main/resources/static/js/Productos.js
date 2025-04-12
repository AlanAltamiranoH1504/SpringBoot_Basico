document.addEventListener("DOMContentLoaded", () => {
    cargarProductos();

    const btnFormulario = document.querySelector("#sendForm");
    const seccionAlertas = document.querySelector("#alertas");
    const tbody_productos = document.querySelector("#tbody-productos");
    btnFormulario.addEventListener("click", formularioEnviado);

    function formularioEnviado(e) {
        e.preventDefault();
        seccionAlertas.innerHTML = "";
        let objetoProducto = {};
        const inputNombre = document.querySelector("#nombre").value;
        const inputDescripcion = document.querySelector("#descripcion").value;
        const inputPrecio = document.querySelector("#precio").value;

        //Validacion
        if (inputNombre.trim() === "" || inputNombre == null) {
            const mensajeAlerta = document.createElement("p");
            mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
            mensajeAlerta.textContent = "Nombre del producto vacio";

            seccionAlertas.classList.add("bg-danger", "fw-bold", "rounded");
            seccionAlertas.appendChild(mensajeAlerta);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
            return;
        }
        if (inputDescripcion.trim() === "" || inputDescripcion == null) {
            const mensajeAlerta = document.createElement("p");
            mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
            mensajeAlerta.textContent = "Descripcion del producto vacia";

            seccionAlertas.classList.add("bg-danger", "fw-bold", "rounded");
            seccionAlertas.appendChild(mensajeAlerta);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
            return;
        }
        if (inputPrecio.trim() === "" || inputDescripcion == null || isNaN(inputPrecio)) {
            const mensajeAlerta = document.createElement("p");
            mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
            mensajeAlerta.textContent = "Formato del precio incorrecto o vacio";

            seccionAlertas.classList.add("bg-danger", "fw-bold", "rounded");
            seccionAlertas.appendChild(mensajeAlerta);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
            return;
        }
        objetoProducto = {
            nombre: inputNombre,
            descripcion: inputDescripcion,
            precio: inputPrecio
        };
        saveProductoDB(objetoProducto);
    }

    function saveProductoDB(producto) {
        fetch("/productos/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(producto)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            const {code} = data;
            if (code == 200) {
                mostrarAlertas("Producto guardado correctamente", true);
                tbody_productos.innerHTML = "";
                cargarProductos();
            }else{
                console.log("Guardado incrrecto");
            }
        }).catch((e) => {
            console.log("Error en peticion al back");
        });
    }

    function mostrarAlertas(mensaje, tipo) {
        if (tipo){
            const mensajeAlerta = document.createElement("p");
            mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
            mensajeAlerta.textContent = `${mensaje}`;

            seccionAlertas.classList.add("bg-success", "fw-bold", "rounded");
            seccionAlertas.appendChild(mensajeAlerta);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
        }else{
            console.log("Alerta mala");
        }
    }

    function cargarProductos() {
        fetch("/productos/list", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then((response) => {
            return response.json();
        }).then((data) => {
            const {productos} = data;
            listarProductos(productos);
        }).catch((error) => {
            console.log("Error en la peticion");
            console.log(e.message);
        })
    }

    function listarProductos(productos) {

        productos.forEach((producto) => {
            const tr_producto = document.createElement("tr");
            tr_producto.innerHTML = `
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.descripcion}</td>
                <td>$ ${producto.precio}</td>
            `;
            tbody_productos.appendChild(tr_producto);
        });
    }
});