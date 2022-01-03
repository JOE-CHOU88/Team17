<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>�R�Q����</title>

    <link rel="stylesheet" href="index,feedback.css">
</head>



<body>

    <div class="menubar ">
        <ul>
            <div class="topbar">
                <img class="img-topbar" src="greenbar.png" alt="greenbar.png">
                <li class="active"><a href="#">�Ⲽ�t��</a>
                    <!-- href�O�N��n�s���쪺�a�� -->
                    <div class="sub-menu-1">
                        <ul>
                            <li><a rel="noopener" target="_blank" href="https://kktix.com/">KKTIX</a></li>
                            <li><a rel="noopener" target="_blank" href="https://www.indievox.com/">indivox</a></li>
                            <li><a rel="noopener" target="_blank" href="https://tixcraft.com/">�ݤ��Ⲽ</a></li>
                            <li><a rel="noopener" target="_blank"
                                    href="https://kham.com.tw/application/utk01/UTK0101_03.aspx">�e���Ⲽ</a>
                            </li>
                            <li><a rel="noopener" target="_blank" href="https://www.opentix.life/">OPENTIX�Ⲽ</a></li>
                            <li><a rel="noopener" target="_blank" href="https://ticket.ibon.com.tw/">ibon�Ⲽ</a></li>
                            <li><a rel="noopener" target="_blank"
                                    href="https://tickets.udnfunlife.com/application/utk01/utk0101_.aspx">udn�Ⲽ</a></li>
                        </ul>
                    </div>
                </li>
            </div>

            <div class="topbar">
                <img class="img-topbar" src="yellowbar.png" alt="greenbar.png">
                <li class="active"><a href="#">�j����t���]</a>
                    <div class="sub-menu-3">
                        <ul>
                            <li class="hover me"><a href="#">�_��</a>
                                <div class="sub-menu-2">
                                    <ul>
                                        <li><a rel="noopener" target="_blank" href="https://tmc.taipei/">�x�_�y�歵�֤���</a>
                                        </li>
                                        <li><a rel="noopener" target="_blank" href="https://npac-ntch.org/zh">���U�|</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="hover me"><a href="#">����</a>
                                <div class="sub-menu-2">
                                    <ul>
                                        <li><a rel="noopener" target="_blank"
                                                href="https://www.npac-ntt.org/index">�x���q�@�|</a></li>
                                    </ul>
                                </div>
                            </li>
                            <li class="hover me"><a href="#">�n��</a>
                                <div class="sub-menu-2">
                                    <ul>
                                        <li><a rel="noopener" target="_blank"
                                                href="https://www.npac-weiwuying.org/">�êZ���a���N��Ƥ���</a></li>
                                        <li><a rel="noopener" target="_blank" href="https://kpmc.com.tw/">�����y�歵�֤���</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li><a href="#">�F��</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </div>

            <div class="topbar">
                <img class="img-topbar" src="greenbar.png" alt="greenbar.png">
                <li class="active"><a href="feedback.jsp">�N�����X</a>
                </li>
            </div>

        </ul>
    </div>



    <div class="card-list">
        <div class="left-card">
            <img width="300" src="logo.png" margin=auto alt="�Z�Q������.png">
        </div>

        <div class="right-card">
            <h1 class="title">�R�Q����</h1>
            <h6 class="subtitle"> �̤�K���R�Ъ�t�j�M����</h6>
			
			<form action='${requestUri}' method='get'>
            	<input class="search-bar" type="text" name="keyword">
            	<input type="button" id="searchBtn" class="search-btn" value="Search">
            	<% //onclick="window.location.href='searchResult.jsp'" %>
            </form>
        </div>

    </div>



    <div class="buttombar-list">
        <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
        <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
        <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
        <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
        <img class="img-buttombar" src="smallgreenbar.png" alt="smallgreenbar.png">
        <img class="img-buttombar" src="smallyellowbar.png" alt="smallyellowbar.png">
    </div>
	
	<!-- �s�������D�{�� -->
    <script src="index.js"></script>
</body>

</html>