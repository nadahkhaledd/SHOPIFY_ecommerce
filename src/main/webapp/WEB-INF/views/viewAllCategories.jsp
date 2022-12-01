<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<h2 style="text-align:center">Categories</h2>

<c:forEach  begin="0" items="${categories}"  var="category" varStatus="loopCounter" >

    <div class="card" style="height:50%">
        <img src=${category.imagePath} alt="Denim Jeans" style="width:100%">
        <h1>${category.name}</h1>
        <p><button onclick="location.href = 'http://localhost:8080/e-commerce/products/getCategoryProducts?categoryId=${category.id}';" id="myButton"  >Check my products</button></p>
    </div>
    <c:if test="${loopCounter.count % 4 == 0}">
        <br>
    </c:if>
</c:forEach>

</body>
</html>