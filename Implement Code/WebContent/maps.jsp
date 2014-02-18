<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="util.MySQLConnection"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>Maps</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	(function($) {
		function hideOptions(speed) {
			if (speed.data) {
				speed = speed.data
			}
			if ($(document).data("nowselectoptions")) {
				$($(document).data("nowselectoptions")).slideUp(speed);
				$($(document).data("nowselectoptions")).prev("div")
						.removeClass("tag_select_open");
				$(document).data("nowselectoptions", null);
				$(document).unbind("click", hideOptions);
				$(document).unbind("keyup", hideOptionsOnEscKey);
			}
		}
		function hideOptionsOnEscKey(e) {
			var myEvent = e || window.event;
			var keyCode = myEvent.keyCode;
			if (keyCode == 27)
				hideOptions(e.data);
		}
		function showOptions(speed) {
			$(document).bind("click", speed, hideOptions);
			$(document).bind("keyup", speed, hideOptionsOnEscKey);
			$($(document).data("nowselectoptions")).slideDown(speed);
			$($(document).data("nowselectoptions")).prev("div").addClass(
					"tag_select_open");
		}

		$.fn.selectCss = function(_speed) {
			$(this)
					.each(
							function() {
								var speed = _speed || "fast";
								if ($(this).data("cssobj")) {
									$($(this).data("cssobj")).remove();
								}
								$(this).hide();
								var divselect = $("<div></div>").insertAfter(
										this).addClass("tag_select");
								$(this).data("cssobj", divselect);
								var divoptions = $("<ul></ul>").insertAfter(
										divselect).addClass("tag_options")
										.hide();
								divselect.click(function(e) {
									if ($($(document).data("nowselectoptions"))
											.get(0) != $(this).next("ul")
											.get(0)) {
										hideOptions(speed);
									}
									if (!$(this).next("ul").is(":visible")) {
										e.stopPropagation();
										$(document).data("nowselectoptions",
												$(this).next("ul"));
										showOptions(speed);
									}
								});
								divselect.hover(function() {
									$(this).addClass("tag_select_hover");
								}, function() {
									$(this).removeClass("tag_select_hover");
								});
								$(this)
										.change(
												function() {
													$(this)
															.nextAll("ul")
															.children(
																	"li:eq("
																			+ $(this)[0].selectedIndex
																			+ ")")
															.addClass(
																	"open_selected")
															.siblings()
															.removeClass(
																	"open_selected");
													$(this)
															.next("div")
															.html(
																	$(this)
																			.children(
																					"option:eq("
																							+ $(this)[0].selectedIndex
																							+ ")")
																			.text());
												});
								$(this)
										.children("option")
										.each(
												function(i) {
													var lioption = $(
															"<li></li>")
															.html(
																	$(this)
																			.text())
															.attr(
																	"title",
																	$(this)
																			.attr(
																					"title"))
															.appendTo(
																	divoptions);
													if ($(this)
															.attr("selected")) {
														lioption
																.addClass("open_selected");
														divselect.html($(this)
																.text());
													}
													lioption.data("option",
															this);
													lioption
															.click(function() {
																lioption
																		.data("option").selected = true;
																$(
																		lioption
																				.data("option"))
																		.trigger(
																				"change",
																				true)
															});
													lioption.hover(function() {
														$(this).addClass(
																"open_hover");
													}, function() {
														$(this).removeClass(
																"open_hover");
													});
												});
							});
		}
	})(jQuery);
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("select").selectCss();
	});
</script>

<script type="text/javascript">
	function Auto() {
		
<%HttpSession s = request.getSession();
			String user = (String) session.getAttribute("Staff");

			Connection con = MySQLConnection.connection();
			String position="";
			try {
				Statement state = con.createStatement();

				String query = "SELECT * FROM staff WHERE name='"
						+user+"';";
				ResultSet rs = state.executeQuery(query);

				while (rs.next()) {
					position = (String) rs.getString("position");
					state.close();
					con.close();
				}
				} catch (SQLException e) {

				}
			if (!position.equals("Curator")&&!position.equals("Dtabase Administer")) {
				response.sendRedirect("/library");
				
			}
			%>
	}
</script>
</head>

<body onload="Auto()">
	<!-- head begin-->
	<div id="head">
    <img src="images/head.gif" border="0" usemap="#Map" />
        <map name="Map" id="Map">
		    <area shape="rect" coords="81,19,280,77" href="index.jsp" />
			<area shape="rect" coords="979,30,1031,53" href="index.jsp" />
			<area shape="rect" coords="1041,30,1081,53" href="#" />
			<area shape="rect" coords="1091,28,1147,54" href="logout" />
			<area shape="rect" coords="1154,25,1180,53" href="index.jsp" />
	    </map>   
	</div>
	<!-- head end-->

	<div class="mid">
		<ul class="mainNav">
			<li class="hover"><a href="index.jsp">Home</a></li>
			<li><a href="personal.jsp">Personal</a></li>
			<li><a href="search.jsp">Search</a></li>
			<li><a href="people.jsp">People</a></li>
			<li><a href="book.jsp">Books</a></li>
			<li><a href="maps.jsp">Maps</a></li>
			<li class="last"><a href="about.jsp">About us</a></li>
		</ul>


		<div class="main">
			<div class="mainTop"></div>
			<div class="mainNr">
				<div class="left02">
					<div class="kg3_top"></div>
					<div class="kg3_center">
						<a href="#"><img src="images/an01.gif" border="0"
							class="leftAn" /></a> <a href="#"><img src="images/an02.gif"
							border="0" class="leftAn" /></a>

						<ul class="tab-hd">
							<li id="tab-hd1" onclick="chenk(1)"><img
								src="images/tab4.gif" width="97" height="26" /></li>
						</ul>
						<div class="mapHd"></div>

					</div>
					<div class="kg3_foot"></div>
				</div>

				<div class="right02">
					<div class="search">
						<form>
							<div class="searchMk" style="margin-left: 20px;">
								<select name="Library" id="select1">
									<option value="1" title="Choose options">Library1</option>
									<option value="2">Library2</option>
									<option value="3">Library3</option>
								</select>
							</div>
							<input name="KeyWord" type="text" class="text" />
							<button></button>
						</form>
					</div>


					<div class="topBj"></div>
					<div class="map"></div>
				</div>
				<div class="clearit"></div>
			</div>
			<div class="mainFoot"></div>
		</div>

		<div id="foot">
			<img src="images/foot.gif" />
		</div>
	</div>

</body>
</html>
