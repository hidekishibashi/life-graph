<template>
<div id="goods">
  <head>
    <title>商品検索</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  </head>
  <body>
    <form method="post" name="form1">
      <table>
        <tr><td>商品ID : <input type="text" class="form-control" id="goods_id" name="goodsId" th:value="${goodsId}"/></td></tr>
        <tr><td>商品名 : <input type="text" class="form-control" id="goods_name" name="goodsName" th:value="${goodsName}"/></td></tr>
        <tr>
          <td>価格帯 : <input type="text" class="form-control" id="price_from" name="priceFrom" th:value="${priceFrom}"/>
          ～ <input type="text" class="form-control" id="price_to" name="priceTo" th:value="${priceTo}"/></td>
        </tr>
        <tr><td><input type="submit" value="検索"/></td></tr>
      </table>
    </form>
    <div th:if="${resultSize > 0}"><label th:text="${resultSize}"></label>件</div>
    <table border="1" th:if="${resultSize > 0}">
      <tr>
        <td>商品ID</td>
        <td>商品名</td>
        <td>価格</td>
      </tr>
      <tr th:each="data : ${result}">
        <td th:text="${data.goodsId}"/>
        <td th:text="${data.goodsName}"/>
        <td th:text="${data.price}"/>
      </tr>
    </table>
  </body>
</div>
</template>
