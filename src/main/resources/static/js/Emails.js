document.addEventListener("DOMContentLoaded", () => {
    const btnsendEmail = document.querySelector("#sendEmail");
    const divAlertasEmail = document.querySelector("#divAlertasEmail");

    //Eventos
    btnsendEmail.addEventListener("click", submitFormEmail);

    //Funciones
    function submitFormEmail(e) {

        e.preventDefault();
        const inputTo = document.querySelector("#to").value;
        const inputSubject = document.querySelector("#subject").value;
        const inputText = document.querySelector("#text").value;

        //Validacion
        if (inputTo.trim() === "" || inputTo == null){
            mostrarAlertas("error", "Campo Destinatario Vacio");
        }
        if (inputSubject.trim() === "" || inputSubject == null){
            mostrarAlertas("error", "Campo Asunto Vacio");
            return;
        }
        if(inputText.trim() === "" || inputText.trim() == null){
            mostrarAlertas("error", "Campo Cuerpo Vacio");
            return;
        }

        //Pasa validacion y mandamos peticion con funcion
        const bodyEmail = {
            to: inputTo,
            subject: inputSubject,
            text: inputText
        }
        peticionSendEmail(bodyEmail);
    }

    function peticionSendEmail(bodyEmail) {
        fetch("/mails/send-email", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(bodyEmail)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            const {message} = data;
            if (message === "Success"){
                mostrarAlertas("success", "Correo enviado correctamente!");
            }
        }).catch((error) => {
            console.log("Error en realizar peticion al back");
            console.log(error.message);
        });
    }

    function mostrarAlertas(tipo, mensaje){
        divAlertasEmail.innerHTML = "";
        if (tipo === "error"){
            document.querySelector("#contenedorAlertas").classList.remove("d-none");
            divAlertasEmail.classList.add("bg-danger");
            divAlertasEmail.textContent = mensaje;
            setTimeout(() => {
                divAlertasEmail.textContent = "";
                document.querySelector("#contenedorAlertas").classList.add("d-none");
            }, 4000);
        }else{
            document.querySelector("#contenedorAlertas").classList.remove("d-none");
            divAlertasEmail.classList.add("bg-success");
            divAlertasEmail.textContent = mensaje;
            limpiarInputs();
            setTimeout(() => {
                divAlertasEmail.textContent = "";
                document.querySelector("#contenedorAlertas").classList.add("d-none");
            }, 4000);
        }
    }

    function limpiarInputs(){
        document.querySelector("#to").value = "";
        document.querySelector("#subject").value = "";
        document.querySelector("#text").value = "";
    }
});