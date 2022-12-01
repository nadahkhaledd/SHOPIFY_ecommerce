<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<h2 style="text-align:center">Product Card</h2>

<c:forEach  begin="0" items="${products}"  var="product" varStatus="loopCounter" >

<div class="card">
    <img src=${product.imagePath} alt="Denim Jeans" style="width:100%">
    <h1>${product.name}</h1>
    <p class="price">${product.price}</p>
    <p>Some text about the jeans. Super slim and comfy lorem ipsum lorem jeansum. Lorem jeamsun denim lorem jeansum.</p>
    <p><button>Add to Cart</button></p>

</div>
    <c:if test="${loopCounter.count % 4 == 0}">
        <br>
    </c:if>
</c:forEach>

</body>
</html>