const tableBody = document.querySelector("#odontologosTable tbody");
function fetchOdontologos() {

    // listando los odontologos
    fetch(`/odontologo`)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            // Limpiar el contenido actual de la tabla
            tableBody.innerHTML = "";

            // Insertar los datos en la tabla
            data.forEach((odontologo, index) => {
                const row = document.createElement("tr");

                row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.matricula}</td>
                <td>
                  <button class="btn btn-info btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.matricula}')">Modificar</button>
                  <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
                </td>
              `;

                tableBody.appendChild(row);
            });
        })
        .catch((error) => {
            console.error("Error fetching data:", error);
        });
}

// eliminar un odontologo
function deleteOdontologo(id) {
    fetch(`/odontologo/${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        }
    })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            alert("Odontólogo eliminado con éxito");
            fetchOdontologos(); // Refrescar la lista de odontólogos
        })
        .catch((error) => {
            console.error("Error eliminando odontólogo:", error);
        });
}


// modificar un odontologo
function editOdontologo(id) {
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const matricula = document.getElementById("matricula").value;

    fetch(`/odontologo`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({id, nombre, apellido, matricula})
    })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            alert("Odontólogo Modificado con éxito");
            fetchOdontologos(); // Refrescar la lista de odontólogos
            clearInputs();

        })
        .catch((error) => {
            console.error("Error Modificando odontólogo:", error);
        });
}
function clearInputs() {
    document.getElementById("nombre").value = "";
    document.getElementById("apellido").value = "";
    document.getElementById("matricula").value = "";
}

fetchOdontologos();