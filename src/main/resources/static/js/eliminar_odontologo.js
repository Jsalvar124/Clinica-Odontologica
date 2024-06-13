fetch(`/odontologo/id`,{
    method: "DELETE",
    headers: {
        "Content-Type": "application/json",
    },
    body: JSON.stringify({nombre, apellido, matricula}),
})
    .then((response) => response.json())
    .then((data) => {
        console.log(data);
        alert("Odontólogo eliminado con éxito");
        form.reset(); // Resetear el formulario
    })
    .catch((error) => {
        console.error("Error eliminando odontólogo:", error);
    });