'use strict'

let listaSituacaoAprovacao = [{id:1,nome:"Pendente"},{id:2,nome:"Aprovado"},{id:3,nome:"Reprovado"},{id:4,nome:"Submetido"}];

const enumSituacaoAprovacao = {
	PENDENTE: 1,
	APROVADO: 2,
	REPROVADO: 3,
	SUBMETIDO: 4
};

function htmlToPlaintext(text) {
	return text ? String(text).replace(/<[^>]+>/gm, '') : '';
}

function formatarIdSituacaoAprovacao(id) {
	let situacao = "";
	listaSituacaoAprovacao.forEach(function (item,index) {
		if(item.id == id){
			situacao = item.nome;
		}
	})
	return situacao;
}