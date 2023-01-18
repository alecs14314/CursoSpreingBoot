// Call the dataTables jQuery plugin
$(document).ready(function () {
	cargarUsuarios()
	$('#dtUsuarios').DataTable()
})

async function cargarUsuarios() {
	const request = await fetch('api/usuarios', {
		method: 'GET',
		headers: getHeaders(),
	})
	usuarios = await request.json()
	console.log(usuarios)
	let rowusuarios = ''
	usuarios.forEach(usuario => {
		let botonEliminar = '<a href="#" onClick="eliminarUsuario(' + usuario['id'] + ')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>'
		rowusuarios += '<tr>'
		rowusuarios += '<td>' + usuario['id'] + '</td>'
		rowusuarios += '<td>' + usuario['nombre'] + ' ' + usuario['apellido'] + '</td>'
		rowusuarios += '<td>' + usuario['email'] + '</td>'
		rowusuarios += '<td>' + usuario['telefono'] + '</td>'
		rowusuarios += '<td>' + botonEliminar + '</td>'
		rowusuarios += '</tr>'
	})
	document.querySelector('#dtUsuarios tbody').outerHTML = rowusuarios
}
async function eliminarUsuario(id) {
	if (!confirm('¿Desea eliminar el Usuario?')) return
	await fetch('api/usuario/' + id, {
		method: 'DELETE',
		headers: getHeaders(),
	})
	location.reload()
}

function getHeaders() {
	return {
		Accept: 'application/json',
		'Content-Type': 'application/json',
		Authorization: localStorage.token,
	}
}
