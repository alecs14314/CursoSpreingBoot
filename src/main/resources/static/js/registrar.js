$(document).ready(function () {})

async function registrarUsuario() {
	let datos = {
		nombre: document.getElementById('fcu_Nombre').value,
		apellido: document.getElementById('fcu_Apellido').value,
		email: document.getElementById('fcu_Email').value,
		telefono: document.getElementById('fcu_Telefono').value,
		password: document.getElementById('fcu_Password').value,
	}
	let passworRepetido = document.getElementById('fcu_RepPassword').value

	if (datos.password != passworRepetido) {
		alert('Password no coincide')
		return
	}
	await fetch('api/usuario', {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(datos),
	})
}
