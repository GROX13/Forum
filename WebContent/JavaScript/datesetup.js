/**
 * 
 */

function populatedropdown(dayfield, monthfield, yearfield) {
	var today = new Date();
	var dayfield = document.getElementById(dayfield);
	var monthfield = document.getElementById(monthfield);
	var yearfield = document.getElementById(yearfield);
	for ( var i = 1; i <= 31; i++)
		dayfield.options[i - 1] = new Option(i, i);
	for ( var m = 1; m <= 12; m++)
		monthfield.options[m - 1] = new Option(m, m);
	var thisyear = today.getFullYear();
	for ( var y = 1; y <= 100; y++) {
		yearfield.options[y - 1] = new Option(thisyear, thisyear--);
	}
}