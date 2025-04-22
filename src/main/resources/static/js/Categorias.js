document.addEventListener("DOMContentLoaded", () => {
    peticionListarCategorias();

    //Selectores
    const btnNewCategoria = document.querySelector("#newCategoria");
    const tbodyCategorias = document.querySelector("#tbody-categorias");
    const formEdicionCategoria = document.querySelector("#formEdicionCategoria");

    //Eventos del DOM
    btnNewCategoria.addEventListener("click", sendFormNuevaCategoria);
    formEdicionCategoria.addEventListener("submit", sendActualizacionCategoria);

    //Funciones
    function peticionListarCategorias() {
        fetch("/categorias/findAll", {
            method: "GET"
        }).then((respose) => {
            return respose.json();
        }).then((data) => {
            listadoCategorias(data)
        }).catch((error) => {
            console.log("Error en peticion a listado de categorias");
            console.log(e.message);
        })
    }

    function listadoCategorias(data) {
        const {categorias} = data;
        tbodyCategorias.innerHTML = "";
        categorias.forEach((categoria) => {
            const trCategoria = document.createElement("tr");
            trCategoria.innerHTML = `
                <td>${categoria.id}</td>
                <td>${categoria.nombre}</td>
                <td>${categoria.slug}</td>
                <td class="d-flex justify-content-around">
                    <button type="button" id="btnEditar" data-id="${categoria.id}" class="btn btn-warning d-flex justify-content-center align-items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-edit"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg>
                    </button>
                    <button type="button" id="btnEliminar" data-id="${categoria.id}" class="btn btn-danger d-flex justify-content-center align-items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x-circle"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg>
                    </button>
                </td>
            `;
            tbodyCategorias.appendChild(trCategoria);
        });
        document.querySelector("#tbody-categorias").addEventListener("click", (e) => {
            btnSeleccionado(e.target)
        });
    }

    function sendFormNuevaCategoria(e) {
        e.preventDefault();
        const inputNombreCategoria = document.querySelector("#nombreCategoria").value;
        const inputSlugCategoria = document.querySelector("#slugCategoria").value;
        const nuevaCategoria = {
            nombre: inputNombreCategoria,
            slug: inputSlugCategoria
        }

        //Validacion
        if (inputNombreCategoria.trim() === "" && inputSlugCategoria.trim() === ""){
            alertasFormCategoria("creacion","error", "Los campos son obligatorios para una categoria");
            return;
        }
        if (inputNombreCategoria.trim() === "" || inputNombreCategoria == null){
            alertasFormCategoria("creacion", "error", "El nombre de la categoria no debe ir vacio");
            return;
        }
        if (inputSlugCategoria.trim() === "" || inputSlugCategoria == null){
            alertasFormCategoria("creacion", "error", "El slug de la categoria no debe ir vacio");
            return;
        }

        fetch("/categorias/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(nuevaCategoria)
        }).then((respose) => {
            return respose.json();
        }).then((data) => {
            limpiarInputs();
            alertasFormCategoria("creacion","success", "Categoria Agregada Correctamente");
            peticionListarCategorias();
        }).catch((error) => {
            console.log("Error en guardado de nueva categoria");
            console.log(error.message);
        })
    }

    function btnSeleccionado(btnSeleccionado) {
        const tipoBtn = btnSeleccionado.getAttribute("id");
        if (tipoBtn === "btnEliminar"){
            const id = btnSeleccionado.getAttribute("data-id")
            preEliminacion(id);
        }else if(tipoBtn === "btnEditar") {
            const id = btnSeleccionado.getAttribute("data-id");
            peticionEdicion(id);
        }
    }

    function preEliminacion(id) {
        const bodyCategoria = {
            id: id
        }
        fetch("/categorias/delete", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(bodyCategoria)
        }).then((respose) => {
            return respose.json();
        }).then((data) => {
            alertasEliminacion("success", "Categoria eliminada");
            peticionListarCategorias();
        }).catch((error) => {
            console.log("Error en peticion para eliminacion");
            console.log(error.message);
        })
    }

    function peticionEdicion(id){
        const bodyCategoria = {
            id
        }
        fetch("/categorias/findById", {
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify(bodyCategoria)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            preEdicion(data)
        }).catch((error) => {
            // console.log("Error en peticion para encontrar categoria");
        });
    }

    function preEdicion(data){
        const {categoria} = data;
        const modalEdicionCategoria = new bootstrap.Modal(document.querySelector("#editarCategoria"));
        modalEdicionCategoria.show();
        document.querySelector("#idCategoriaEdicion").value = categoria.id;
        document.querySelector("#nombreCategoriaActualizar").value = categoria.nombre;
        document.querySelector("#slugCategoriActualizar").value = categoria.slug;
    }

    function sendActualizacionCategoria(e){
        e.preventDefault();
        const inputNombreActualizar = document.querySelector("#nombreCategoriaActualizar").value;
        const inputSlugActualizar = document.querySelector("#slugCategoriActualizar").value;
        const inputId = document.querySelector("#idCategoriaEdicion").value;

        if (inputSlugActualizar.trim() === "" && inputNombreActualizar.trim() === ""){
            alertasFormCategoria("edicion", "error", "Los campos son obligatorios");
            return;
        }
        if (inputNombreActualizar.trim() === "" || inputNombreActualizar == null){
            alertasFormCategoria("edicion", "error", "El nombre no puede estar vacio");
            return;
        }
        if (inputSlugActualizar.trim() === "" || inputSlugActualizar == null){
            alertasFormCategoria("edicion", "error", "El slug no puede estar vacio");
            return;
        }

        const categoriaBody = {
            id: inputId,
            nombre: inputNombreActualizar,
            slug: inputSlugActualizar
        }
        fetch("/categorias/updateCategoria", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(categoriaBody)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            alertasFormCategoria("edicion", "success", "Categoria actualizada de forma correcta");
            peticionListarCategorias();
        }).catch((error) => {
            console.log("Error en peticion al backend");
            console.log(error.message);
        })
    }

    function alertasFormCategoria(formulario, tipo, mensaje) {

        if (formulario === "creacion") {
            const alertarFormCategorias = document.querySelector("#alertarFormCategorias");
            if (tipo === "success") {
                alertarFormCategorias.classList.add("bg-success");
                alertarFormCategorias.textContent = mensaje;

                setTimeout(() => {
                    alertarFormCategorias.classList.remove("bg-success");
                    alertarFormCategorias.textContent = "";
                },3500)
            }else{
                alertarFormCategorias.classList.add("bg-warning");
                alertarFormCategorias.textContent = mensaje;

                setTimeout(() => {
                    alertarFormCategorias.classList.remove("bg-warning");
                    alertarFormCategorias.textContent = "";
                },3500);
            }
        } else if (formulario === "edicion") {
            const alertarFormCategoriasEdicion = document.querySelector("#alertas_edicion");
            if (tipo === "success") {
                alertarFormCategoriasEdicion.classList.add("bg-success");
                alertarFormCategoriasEdicion.textContent = mensaje;

                setTimeout(() => {
                    alertarFormCategoriasEdicion.classList.remove("bg-success");
                    alertarFormCategoriasEdicion.textContent = "";
                },3500)
            }else{
                alertarFormCategoriasEdicion.classList.add("bg-warning");
                alertarFormCategoriasEdicion.textContent = mensaje;

                setTimeout(() => {
                    alertarFormCategoriasEdicion.classList.remove("bg-warning");
                    alertarFormCategoriasEdicion.textContent = "";
                },3500);
            }
        }
    }

    function alertasEliminacion(tipo, mensaje) {
        const alertas = document.querySelector("#alertas_fuera-categorias");
        if (tipo === "success") {
            alertas.classList.add("bg-success");
            alertas.textContent = mensaje;

            setTimeout(() => {
                alertas.classList.remove("bg-success");
                alertas.textContent = "";
            },3500)
        }else{
            alertas.classList.add("bg-warning");
            alertas.textContent = mensaje;

            setTimeout(() => {
                alertas.classList.remove("bg-warning");
                alertas.textContent = "";
            },3500);
        }
    }
    function limpiarInputs() {
        document.querySelector("#nombreCategoria").value = "";
        document.querySelector("#slugCategoria").value = "";
    }
})