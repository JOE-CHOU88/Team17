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
    <!-- header -->
    <!-- introSection -->
    <!-- �]�wintroSection �W�Upadding��5�� -->
	<%
String[][] orderList = (String[][]) request.getAttribute("sortedQuery");
for(int i =0 ; i < orderList.length;i++){%>
	<a href='<%= orderList[i][1] %>'><%= orderList[i][0] %></a><br>
	<%=orderList[i][2]%>
	<br><br>	
<%
}
%>
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
                <h1 class="title">�R�Q����</h1>
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
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="work-box">
                        <div class="icon text-primary py-3">
                            <img src="https://picsum.photos/id/1037/300/300" alt="">
                        </div>
                        <div>
                            <p>
                                <strong>A<br></strong>
                                Host:<br>Place:<br>Price:<br>Time:
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="work-box">
                        <div class="icon text-primary py-3">
                            <img src="https://picsum.photos/id/1037/300/300" alt="">
                        </div>
                        <p>
                            <strong>A<br></strong>
                            Host:<br>Place:<br>Price:<br>Time:
                        </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="work-box">
                        <div class="icon text-primary py-3">
                            <img src="https://picsum.photos/id/1037/300/300" alt="">
                        </div>
                        <div>
                            <p>
                                <strong>A<br></strong>
                                Host:<br>Place:<br>Price:<br>Time:
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="work-box">
                        <div class="icon text-primary py-3">
                            <img src="https://picsum.photos/id/1037/300/300" alt="">
                        </div>
                        <p>
                            <strong>A<br></strong>
                            Host:<br>Place:<br>Price:<br>Time:
                        </p>
                    </div>
                </div>
            </div>
        </div>
        </div>
        </div>
    </section>
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