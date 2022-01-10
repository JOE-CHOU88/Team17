<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>�R�Q����</title>
    <link rel="icon shortcut" href="./img/favicon.ico">
    <!-- Bootstrap�x����� https://getbootstrap.com/ -->
    <!-- �s��Bootstrap.min.css -->
    <!-- �Ȼs��Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap.css">
    <!-- �ϥ�font awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
        integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <!-- �ϥ�style.css -->
    <link rel="stylesheet" href="searchResult.css">
</head>

<body>
		<% 
		/*
		<a href='<%=url'><%=title</a> <br>Total score: <%=score<br>
		<br>
		*/ 
		%>
		
    <!-- header -->
    <!-- introSection -->
    <!-- �]�wintroSection �W�Upadding��5�� -->
    <section id="introSection">
        <div class="buttombar-list">
            <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
            <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
            <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
            <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
            <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
            <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
        </div>
        <div class="card-list">
            <div class="left-card">
                <h1 class="title"><a href='http://localhost:8080/DS_FinalProject2/TestProject'>�R�Q����</a></h1>
            </div>
            <div class="right-card">
                <div class="select-card-list">
                    <select class="select-card">
                        <option>�̦a��</option>
                        <option>����</option>
                        <option>�_��</option>
                        <option>����</option>
                        <option>�n��</option>
                        <option>�F��</option>
                    </select>
                    <select class="select-card">
                        <option>�̮ɶ�</option>
                        <option>����</option>
                        <option>�@�Ӥ뤺</option>
                        <option>�T�Ӥ뤺</option>
                        <option>���Ӥ뤺</option>
                    </select>
                    <select class="select-card">
                        <option>�̻���</option>
                        <option>�ѧC�찪</option>
                        <option>�Ѱ���C</option>
                    </select>
                </div>
            </div>

        </div>
<%
		String[][] orderList = (String[][]) request.getAttribute("sortedWebList");
		//System.out.println(orderList[0][0]); //title
		//System.out.println(orderList[0][1]); //url
		for (int i = 0; i < orderList.length; i+=3) {
			int maxSizeOfTitle=20;
			String title = "";
			//String url = orderList[i][1];
			//String title = orderList[i][0];
			//String score =  orderList[i][2];
			//s=s.substring(7);
%>
        
        <div class="container">
            <div class="row">
<%/*
			if(orderList[i][0].length()>maxSizeOfTitle) {
				title = orderList[i][0].substring(0,maxSizeOfTitle) + "...";
			}else{
				title = orderList[i][0];
			}
*/%>
                <div class="col-md-4">
                    <div class="work-box">
                        <div class="icon text-primary py-3">
                            <img src="https://picsum.photos/id/1037/300/300" alt="">
                        </div>
                        <div>
                            <p>
                                <strong><a href='<%=orderList[i][1]%>'><%=orderList[i][0]%> </a><br></strong>
                            	<br>Total score: <%=orderList[i][2]%><br>
                            	Host:<br>Place:<br>Price:<br>Time:
								<br>
                            </p>
                        </div>
                    </div>
                </div>
<%/*
			if(orderList[i+1][0].length()>maxSizeOfTitle) {
				title = orderList[i+1][0].substring(0,maxSizeOfTitle) + "...";
			}else{
				title = orderList[i+1][0];
			}
*/%>               
                <div class="col-md-4">
                    <div class="work-box">
                        <div class="icon text-primary py-3">
                            <img src="https://picsum.photos/id/1037/300/300" alt="">
                        </div>
                        <p>
                            <strong><a href='<%=orderList[i+1][1]%>'><%=orderList[i+1][0]%> </a><br></strong>
                            <br>Total score: <%=orderList[i+1][2]%><br>
                            Host:<br>Place:<br>Price:<br>Time:
                        </p>
                    </div>
                </div>
<%/*			
			if(orderList[i+2][0].length()>maxSizeOfTitle) {
				title = orderList[i+2][0].substring(0,maxSizeOfTitle) + "...";
			}else{
				title = orderList[i+2][0];
			}
*/%>
                <div class="col-md-4">
                    <div class="work-box">
                        <div class="icon text-primary py-3">
                            <img src="https://picsum.photos/id/1037/300/300" alt="">
                        </div>
                        <div>
                            <p>
                                <strong><a href='<%=orderList[i+1][1]%>'><%=orderList[i+2][0]%> </a><br></strong>
                            	<br>Total score: <%=orderList[i+2][2]%><br>
                                Host:<br>Place:<br>Price:<br>Time:
                            </p>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
        </div>
        </div>
    </section>
    
<%
}
%>
    <!-- introSection end -->
    <!-- �]�w�m����^���s ��^���s�u�ʰʵe -->
    <button id="goBackBtn" class="bg-primary">
        <i class="fas fa-chevron-up"></i>
    </button>

    <!-- jquery.min.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- popper.min.js -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <!-- bootstrap.min.js -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
    <!-- �s�������D�{�� -->
    <script src="searchResult.js"></script>
    
</body>

</html>