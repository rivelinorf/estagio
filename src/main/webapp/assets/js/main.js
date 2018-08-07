
$(document).ready(function() {
	/*
	 * $(".btn-title").on("click", function () { for (var i = 0; i <
	 * $(".btn-title").length; ++i) { if ($(this)[0].classList[2] ===
	 * $(".dropdown-content")[i].classList[1]) {
	 * $("#"+$(".dropdown-content")[i].id).toggle(200) } else {
	 * $("#"+$(".dropdown-content")[i].id).hide(200) } } });
	 */

	$("#estado").on("click", function() {
		$.ajax({
			url : "/views/estado.jsp",
			type : "GET",
			success : function(data) {
				$("#table-content").html(data).fadeIn(500)
			}
		})
	});
	
	$("#tipologradouro").on("click", function() {
		$.ajax({
			url : "/views/tipologradouro/lista.jsp",
			type : "GET", 
			success : function(data) {
				$("#table-content").html(data).fadeIn(500)
			}
		})
	});
	
	$("#logradouro").on("click", function() {
		$.ajax({
			url : "/views/logradouro/lista.jsp",
			type : "GET",
			success : function(data) {
				$("#table-content").html(data).fadeIn(500)
			}
		})
	});

	$("#bairro").on("click", function() {
		$.ajax({
			url : "/views/bairro/lista.jsp",
			type : "GET",
			success : function(data) {
				$("#table-content").html(data).fadeIn(500)
			}
		})
	});
	
	$("#endereco").on("click", function() {
		$.ajax({
			url : "/views/endereco/lista.jsp",
			type : "GET",
			success : function(data) {
				$("#table-content").html(data).fadeIn(500)
			}
		})
	});

});

