$(function() {
	$('#loginButton').click(login);
	//$('#voteButton').click(vote);
});

$(document).on('click', 'a', function(event) {
	event.preventDefault();
	if ($(this).attr('href') === '#votePage') {
		configuration.requirementIndex = parseInt($(this).attr('data-requirement-index'));
		updateVotePage();
	}
	$.mobile.navigate($(this).attr('href'));
});

function login() {
	var project = 'default';
	var index = 1;
	var username = $('#username').val();
	window.configuration = {};
	window.configuration.username = username;
	var password = $('#password').val();
	
	if (username == '' || password == '') {
		alert('Please enter your username and password.');
		return;
	}
	
	networkLogin(username, password, function() {
		networkProject(project, function() {
			networkGetSession(index, function() {
				$('#sessionName').text(configuration.session.Name);
				$('#sessionDescription').text(configuration.session.Description);
				updateRequirementLists();
				location.hash = 'overviewPage';
			});
		});
	});
}

function networkLogin(username, password, callback) {
	var auth = 'Basic ' + window.btoa(username + ':' + password);
	$.ajax({
		url: '/WPISuite/API/login',
		type: 'POST',
		headers: { 'Authorization': auth },
		success: callback,
		error: function() { alert('Login failed.'); }
	});
}

function networkProject(project, callback) {
	$.ajax({
		url: '/WPISuite/API/login',
		type: 'PUT',
		datatype: 'text',
		data: project,
		success: callback,
		error: function() { alert('Login failed.'); }
	});
}

function networkGetSession(index, callback) {
	$.ajax({
		url: '/WPISuite/API/planningpoker/planningpokersession/' + index,
		type: 'GET',
		success: function(data) {
			window.configuration.session = JSON.parse(data)[0];
			callback();
		},
		error: function() { alert('Login failed.'); }
	});
}

function updateRequirementLists() {
	$('#pendingList').empty();
	$('#votedList').empty();
	
	configuration.session.RequirementEstimates.forEach(function(value, index) {
		var output = '<li><a href="#votePage" data-requirement-index="' + index + '">'
						+ value.name + '</a></li>';
		if (configuration.username in value.votes) {
			$('#votedList').append(output);
		} else {
			$('#pendingList').append(output);
		}
	});
}

function updateVotePage() {
	$('#requirementName').empty();
	$('#requirementDescription').empty();
	var requirement = configuration.session.RequirementEstimates[configuration.requirementIndex];
	
	$('#requirementName').text(requirement.name);
	$('#requirementDescription').text(requirement.description);
	
	if (configuration.username in requirement.votes) {
		$('#vote').val(requirement.votes[configuration.username].totalEstimate);
		$('#voteButton').text('Vote again');
	} else {
		$('#vote').val('');
		$('#voteButton').text('Vote');
	}
}