function getMusicas() {
    document.getElementById('load').style.display = '';

    fetch("http://localhost:8080/MusicasCRUD/MusicaRest")
    .then(resp => resp.json())
    .then(dados => listarMusicas(dados));
}

function postMusica(obj) {
    document.getElementById('load').style.display = '';

    var body = JSON.stringify(obj);
    fetch("http://localhost:8080/MusicasCRUD/MusicaRest",
        {
            method: "POST",
            body: body,
        })
    .then(resp => resp.json())
    .then(function (retorno) {
        document.getElementById('load').style.display = 'none';
        openModal(retorno.mensagem)

        voltarTela();
    });
}

function putMusica(obj) {
    document.getElementById('load').style.display = '';

    var body = JSON.stringify(obj);
    fetch("http://localhost:8080/MusicasCRUD/MusicaRest",
        {
            method: "PUT",
            body: body,
        })
    .then(resp => resp.json())
    .then(function (retorno) {
        document.getElementById('load').style.display = 'none';
        openModal(retorno.mensagem)

        voltarTela();
    });
}

function deleteMusica(idmusica) {
    document.getElementById('load').style.display = '';

    fetch("http://localhost:8080/MusicasCRUD/MusicaRest/" + idmusica,
        {
            method: "DELETE",
        })
    .then(resp => resp.json())
    .then(function (retorno) {
        document.getElementById('load').style.display = 'none';
        openModal(retorno.mensagem)

        voltarTela();
    });
}