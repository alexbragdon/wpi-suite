$(function() {
	$('#loginButton').click(login);
	$('#voteButton').click(vote);
	
	window.configuration = {};
	var matches = document.URL.match(/planningpoker\/([^\/]+)\/([\d]+)\//);
	if (matches !== null) {
		configuration.project = matches[1];
		configuration.sessionIndex = parseInt(matches[2]);
	} else {
		console.log('Unable to read URL configuration.');
		configuration.project = '';
		configuration.sessionIndex = 1;
	}
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
	var username = $('#username').val();
	configuration.username = username;
	var password = $('#password').val();
	
	if (username == '' || password == '') {
		alert('Please enter your username and password.');
		return;
	}
	
	networkLogin(username, password, function() {
		networkProject(configuration.project, function() {
			networkGetSession(configuration.sessionIndex, function() {
				if (configuration.session.isActive && !configuration.session.isComplete) {
					$('#sessionName').text(configuration.session.Name);
					$('#sessionDescription').text(configuration.session.Description);
					updateRequirementLists();
					location.hash = 'overviewPage';
				} else {
					location.hash = 'closedPage';
				}
			});
		});
	});
}

function vote() {
	var estimate = parseInt($('#vote').val());
	if (isNaN(estimate) || estimate < 0 || estimate > 100) {
		alert('Please enter an estimate between 0 and 100.');
	} else {
		networkGetSession(configuration.sessionIndex, function() {
			if (configuration.session.isActive && !configuration.session.isComplete) {
				configuration.session
					.RequirementEstimates[configuration.requirementIndex]
					.votes[configuration.username] = {
						'user': configuration.username,
						'selectedCardIndices': [],
						'totalEstimate': estimate
				};
				networkUpdateSession(configuration.session, function() {
					updateRequirementLists();
					location.hash = 'overviewPage';
				});
			} else {
				location.hash = 'closedPage';
			}
		});
	}
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

function networkUpdateSession(session, callback) {
	$.ajax({
		url: '/WPISuite/API/planningpoker/planningpokersession/' + session.ID,
		type: 'POST',
		datatype: 'json',
		data: JSON.stringify(session),
		success: callback,
		error: function() { alert('There was a problem saving your vote.'); }
	});
}

function updateRequirementLists() {	
	var pendingOutput = '';
	var votedOutput = '';
	
	configuration.session.RequirementEstimates.forEach(function(value, index) {
		var output = '<li><a href="#votePage" data-requirement-index="' + index + '">'
						+ value.name + '</a></li>';
		if (configuration.username in value.votes) {
			votedOutput += output;
		} else {
			pendingOutput += output;
		}
	});
	
	$('#pendingList').html(pendingOutput).promise().done(function() {
		$(this).listview().listview('refresh');
	});
	$('#votedList').html(votedOutput).promise().done(function() {
		$(this).listview().listview('refresh');
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