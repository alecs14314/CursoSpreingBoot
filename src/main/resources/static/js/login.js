$(document).ready(function () {})

async function login() {
	let datos = {
		nombre: '',
		apellido: '',
		email: document.getElementById('fcu_Email').value,
		telefono: '',
		password: document.getElementById('fcu_Password').value,
	}
	const request = await fetch('api/login', {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(datos),
	})
	const resultado = await request.text()
	if (resultado != 'FAIL') {
		localStorage.token = resultado
		localStorage.email = datos.email
		window.location.href = 'usuarios.html'
	} else alert('Usuario o Password incorrecto')
}
