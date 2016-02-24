$(document).ready(function() {
var isAddGenreFormShow = 0;
var isEditGenreFormShow = 0;
var isEditLinkFormShow = 0;

//ADD GENRE FORM
$(document).on("click", "#genres > .addcontent > .add", function() {
	if (isAddGenreFormShow == 0) {
		var input = "<form id='formaddgenre' method='POST'>"
			+ "<div>Name:</div> <input name='name' id='namegenre'/>"
			+ "<input type='submit' value='Submit'/>"
			+ "</form>";
		
		$("#genres .addgenre").html(input).slideDown("fast");
		isAddGenreFormShow = 1;
	} else {
		var $clazz = $(".addgenre");
		$clazz.slideUp("fast");
		setTimeout(function() {
			$clazz.empty().css("display", "none");
		} , 200);
		isAddGenreFormShow = 0;
	}
});

//ADD GENRE BY SUBMIT
$(document).on("submit", "#formaddgenre", function(event) {
	var iduser = $(this).parents(".addcontent").attr("class").split("-")[1];
	$.ajax({
		type: "GET",
		url: "genre/add.html",
		data: {
			"name": $("#namegenre").val(),
			"iduser": iduser
		},
		
		success: function(idgenre) {
			var namegenre = $("#namegenre").val();
			var input = "<div class='genre genre-" + idgenre + "-" + iduser + "'>"
				+ "<div class='name'><strong>" + namegenre + "</strong>"
				+ "<div class='settings'>"
				+ "<a action='edit'><img class='edit' title='Edit genre' src='/Link4U/resources/images/edit.png' /></a>"
				+ "<a action='delete'><img class='delete' title='Delete genre' src='/Link4U/resources/images/delete.png' /></a>"
				+ "</div>"
				+ "</div>"
				+ "<div class='addcontent addcontent-" + idgenre + "-" + iduser + "'>"
				+ "<img class='add' title='Add genre' src='/Link4U/resources/images/add.png' />"
				+ "<div class='addlink'></div>"
				+ "</div>"
				+ "</div>";
			$(input).insertBefore("#genres > .addcontent");
			$(".addgenre").empty().css("display", "none");
			$("#genres > .addcontent").css("border", "0");
			isAddGenreFormShow = 0;
		},
		error: function(xhr, status, error) {
			alert("There are some problems!");
		}
	});
	
	return false;
});

//SETTINGS GENRE
$(document).on("click", ".genre > .name > .settings > a", function(event) {
	var action = $(this).attr("action");
	if (action === "edit") {
		editGenre($(this));
	}
	
	if (action == "delete") {
		deleteGenre($(this));
	}
});

//EDIT GENRE
function editGenre(obj) {
	var nameofclass = obj.parents(".genre").attr("class");
	var classtoedit = nameofclass.split(" ")[1];
	var idgenre = nameofclass.split("-")[1];
	var iduser = nameofclass.split("-")[2];
	var namegenre = obj.parents(".genre").children(".name").text(); //TODO why there are a lot of white chars?
	if (isEditGenreFormShow == 0) {
		var input = "<div style='display: none' class='editgenre'>"
			+ "<form id='fotmeditgenre' method='POST'>"
			+ "<div>Name:</div><input name='name' id='namegenre' value='" + namegenre + "'/>"
			+ "<input type='submit' value='Submit'/>"
			+ "</form>"
			+ "</div>";
		$(input).insertAfter("." + classtoedit + " > .name");
		$(".editgenre").slideDown("fast");
		isEditGenreFormShow = 1;
	} else {
		var $clazz = $(".editgenre");
		$clazz.slideUp("fast");
		setTimeout(function() {
			$clazz.empty().css("display", "none").slideDown("fast");
		} , 200);
		isEditGenreFormShow = 0;
	}
}

//EDIT GENRE BY SUBMIT
$(document).on("submit", "#fotmeditgenre", function(event) {
	var nameofclass = $(this).parents(".genre").attr("class");
	var classtoedit = nameofclass.split(" ")[1];
	var idgenre = nameofclass.split("-")[1];
	var namegenre = $("#namegenre").val();
	var iduser = nameofclass.split("-")[2];
	$.ajax({
		type: "GET",
		url: "genre/edit.html",
		data: {
			"id": idgenre,
			"name": namegenre,
			"iduser": iduser
		},
	
		success: function(msg) {
			$("." + classtoedit + " > .editlink").slideUp("fast");
			setTimeout(function() {
				$("." + classtoedit + " > .name > strong").text(namegenre);
				$(".editgenre").remove();
			} , 200);
			isEditLinkFormShow = 0;
		},
		error: function(xhr, status, error) {
			alert("There are some problems!");
		}
	});	
	
	return false;
});

//REMOVE GENRE
function deleteGenre(obj) {
	var nameofclass = obj.parents(".genre").attr("class");
	var classtodelete = nameofclass.split(" ")[1];
	var idgenre = nameofclass.split("-")[1];
	var iduser = nameofclass.split("-")[2];

	if (confirm("Are you sure to remove it?")) { 
		$.ajax({
			type: "GET",
			url: "genre/delete.html",
			data: {
				"idgenre": idgenre,
				"iduser": iduser
			},
		
			success: function(msg) {
				$("." + classtodelete).remove();
			},
			error: function(xhr, status, error) {
				alert("There are some problems!");
			}
		});
	}
}

//ADD LINK FORM
$(document).on("click", ".genre > .addcontent > .add", function() {
	var nameofclass = $(this).parent().attr("class");
	var idgenre = nameofclass.split("-")[1];
	var iduser = nameofclass.split("-")[2];
	var $classtoedit = $(".addcontent-" + idgenre + "-" + iduser + " > .addlink");
	
	var $clazz = $(".addlink");
	$clazz.slideUp("fast");
	setTimeout(function() {
		$clazz.empty().css("display", "none");
	}, 100);
	
	if ($classtoedit.contents().length == 0) {
		setTimeout(function() {
			var input = "<form id='formaddlink' method='POST'>"
				+ "<div>Address:</div><input name='address' id='addresslink'/>"
				+ "<div>Description:</div><input name='description' id='descriptionlink'/>"
				+ "<input type='submit' value='Submit'/>"
				+ "</form>";
			$classtoedit.html(input).slideDown("fast");
		}, 100);
	}
});	

//ADD LINK BY SUBMIT
$(document).on("submit", "#formaddlink", function(event) {
	var nameofclass = $(this).parent().parent().attr("class");
	var idgenre = nameofclass.split("-")[1];
	var iduser = nameofclass.split("-")[2];
	var addresslink = $("#addresslink").val();
	console.log( "Before Ajax" );
	console.log( addresslink );
	//checking and adding http://
	if (!addresslink.match(/^http([s]?):\/\/.*/)) {
		addresslink = 'http://' + addresslink;
	}
	
	$.ajax({
		type: "GET",
		url: "link/add.html",
		data: {
			"address": addresslink,
			"description": $("#descriptionlink").val(),
			"idgenre": idgenre,
			"iduser": iduser
		},
		
		success: function(idlink) {
			var addresslink = $("#addresslink").val();
			var descriptionlink = $("#descriptionlink").val();
			var nameofclass = ".addcontent-" + idgenre + "-" + iduser;
			var nameofformclass = ".addcontent-" + idgenre + "-" + iduser + " > .addlink";
			console.log( "After Ajax" );
			console.log( addresslink );
			var input = "<div class='link link-" + idlink + "-" + idgenre + "-" + iduser + "'>"
				+ "<a class='address' href='" + addresslink + "' title='" + addresslink + "'>link</a>"
				+ "<div class='description'>" + descriptionlink + "</div>"
				+ "<div class='settings'>"
				+ "<a action='edit'><img class='edit' title='Edit link' src='/Link4U/resources/images/edit.png' /></a>"
				+ "<a action='delete'><img class='delete' title='Delete link' src='/Link4U/resources/images/delete.png' /></a>"
				+ "</div>"
				+ "</div>";
			$(input).insertBefore(nameofclass);
			$(nameofformclass).empty().css("display", "none");
		},
		error: function(xhr, status, error) {
			alert("There are some problems!");
		}
	});
	
	return false;
});

//SETTINGS LINK
$(document).on("click", ".link > .settings > a", function(event) {
	var action = $(this).attr("action");
	if (action === "edit") {
		editLink($(this));
	}
	
	if (action == "delete") {
		deleteLink($(this));
	}
});

//EDIT LINK
function editLink(obj) {
	var nameofclass = obj.parents(".link").attr("class");
	var clickedtoedit = nameofclass.split(" ")[1];
	var idlink = nameofclass.split("-")[1];
	var idgenre = nameofclass.split("-")[2];
	var iduser = nameofclass.split("-")[3];
	var addresslink = obj.parents(".link").children(".address").attr("title");
	var descriptionlink = obj.parents(".link").children(".description").text();
	
	if (isEditLinkFormShow == 0) {
		var input = "<div style='display: none' class='editlink'>"
			+ "<form id='formeditlink' method='POST'>"
			+ "<div>Address:</div><input name='address' id='addresslink' value='" + addresslink + "'/>"
			+ "<div>Description:</div><input name='description' id='descriptionlink' value='" + descriptionlink + "'/>"
			+ "<input type='submit' value='Submit'/>"
			+ "</form>"
			+ "</div>";
		$("." + clickedtoedit).append(input);
		$(".editlink").slideDown("fast");
		isEditLinkFormShow = 1;
	} else {
		$("." + clickedtoedit + " > .editlink").slideUp("fast");
		setTimeout(function() {
			$(".editlink").remove();
		} , 200);
		isEditLinkFormShow = 0;
	}
}

//EDIT LINK BY SUBMIT
$(document).on("submit", "#formeditlink", function(event) {
	var nameofclass = $(this).parents(".link").attr("class");
	var classtoedit = nameofclass.split(" ")[1];
	var idlink = nameofclass.split("-")[1];
	var address = $("#addresslink").val();
	var description = $("#descriptionlink").val();
	var idgenre = nameofclass.split("-")[2];
	var iduser = nameofclass.split("-")[3];
	$.ajax({
		type: "GET",
		url: "link/edit.html",
		data: {
			"id": idlink,
			"address": address,
			"description": description,
			"idgenre": idgenre,
			"iduser": iduser
		},
	
		success: function(msg) {
			$("." + classtoedit + " > .description").text(description);
			$("." + classtoedit + " > .address").attr("title", address).attr("href", address);
			$("." + classtoedit + " > .editlink").slideUp("fast");
			setTimeout(function() {
				$("." + classtoedit + " > .editlink").remove();
			} , 200);
			isEditLinkFormShow = 0;
		},
		error: function(xhr, status, error) {
			alert("There are some problems!");
		}
	});	
	
	return false;
});

//REMOVE LINK
function deleteLink(obj) {
	var nameofclass = obj.parents(".link").attr("class");
	var classtodelete = nameofclass.split(" ")[1];
	var idlink = nameofclass.split("-")[1];
	var idgenre = nameofclass.split("-")[2];
	var iduser = nameofclass.split("-")[3];

	if (confirm("Are you sure to remove it?")) {
		$.ajax({
			type: "GET",
			url: "link/delete.html",
			data: {
				"id": idlink,
				"idgenre": idgenre,
				"iduser": iduser
			},
		
			success: function(msg) {
				$("." + classtodelete).remove();
			},
			error: function(xhr, status, error) {
				alert("There are some problems!");
			}
		});
	}
}

})