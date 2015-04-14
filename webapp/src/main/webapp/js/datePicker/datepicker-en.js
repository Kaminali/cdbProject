(function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		define([ "js/datePicker/jquery.ui.datepicker" ], factory );
	} else {

		factory( jQuery.datepicker );
	}
}(function( datepicker ) {
	datepicker.regional['en'] = { 
			closeText: "Done",
			prevText: "Prev",
			nextText: "Next",
			currentText: "Today",
			monthNames: ["January","February","March","April","May","June",
				"July","August","September","October","November","December"],
			monthNamesShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
			dayNames: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
			dayNamesShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
			dayNamesMin: ["Su","Mo","Tu","We","Th","Fr","Sa"], 
			weekHeader: "Wk",
			dateFormat: "DD, d MM, yy",
			firstDay: 0,
			isRTL: false,
			showMonthAfterYear: false,
			yearSuffix: ""
		};
	datepicker.setDefaults(datepicker.regional['en']);

	return datepicker.regional['en'];

}));
