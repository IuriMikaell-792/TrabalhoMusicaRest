var listaMusicas = [];

function initCadastroMusica() {
    getMusicas();
}

function listarMusicas(dados) {
    listaMusicas = dados;
    var lista = document.getElementById("tabelaMusicas");
    lista.innerHTML = "";

    if (dados.length == 0) {
        document.getElementById('tabelaMusicas').innerHTML = '<tr><td>Nenhum registro a ser listado</td></tr>'
    } else {
        for (var i in dados) {
            lista.innerHTML += "<tr>"
                            + "<td class='colunaID'>" + dados[i].idmusica + "</td>"
                            + "<td>" + dados[i].nome + "</td>"
                            + "<td>" + dados[i].cantor + "</td>"
                            + "<td>" + dados[i].genero + "</td>"
                            + "<td class='icone'><img src='/img/editar.png' alt='' onclick='alteraCadastro(" + i + ")' /></td>"
                            + "<td class='icone'><img src='/img/excluir.png' alt='' onclick='excluirCadastro(" + dados[i].idmusica + ")' /></td>"
                            + "</tr>";
        }
    }

    document.getElementById('load').style.display = 'none';
}

function novoCadastro() {
    var form = document.getElementById("cadastroMusica");
    var lista = document.getElementById("tabela");
    form.style.display = "block";
    lista.style.display = "none";
    // -------------------------------------------------------------------//
    document.getElementById("inputNome").value = "";
    document.getElementById("inputCantor").value = "";
    document.getElementById("inputGenero").value = "";
    // -------------------------------------------------------------------//
    document.getElementById("btnSalvar").onclick = function () {
        salvarCadastro(true);
    }
    document.getElementById("btnCancelar").onclick = function () {
        voltarTela();
    }
}

function alteraCadastro(i) {
    var form = document.getElementById("cadastroMusica");
    var lista = document.getElementById("tabela");
    form.style.display = "block";
    lista.style.display = "none";
    // -------------------------------------------------------------------//
    document.getElementById("inputNome").value = listaMusicas[i].nome;
    document.getElementById("inputCantor").value = listaMusicas[i].cantor;
    document.getElementById("inputGenero").value = listaMusicas[i].genero;
    // -------------------------------------------------------------------//
    document.getElementById("btnSalvar").onclick = function () {
        salvarCadastro(false, listaMusicas[i].idmusica);
    }
    document.getElementById("btnCancelar").onclick = function () {
        voltarTela();
    }
}

function salvarCadastro(novo, idmusica) {
    var nome = document.getElementById("inputNome").value;
    var cantor = document.getElementById("inputCantor").value;
    var genero = document.getElementById("inputGenero").value;

    if (novo == true) {
        var obj = {
            'nome': nome,
            'cantor': cantor,
            'genero': genero
        };

        postMusica(obj);
    } else {
        var obj = {
            'idmusica': idmusica,
            'nome': nome,
            'cantor': cantor,
            'genero': genero
        };

        putMusica(obj);
    }
}

function excluirCadastro(idmusica) {
    deleteMusica(idmusica);
}

function voltarTela() {
    var form = document.getElementById("cadastroMusica");
    var lista = document.getElementById("tabela");
    form.style.display = "none";
    lista.style.display = "block";
    // -------------------------------------------------------------------//
    getMusicas();
}

function openModal(msg) {
    document.getElementById('modTxt').textContent = msg;
    document.getElementById('modalMsg').style.display = "block";
}

function closeModal() {
    document.getElementById('modalMsg').style.display = "none";
}
