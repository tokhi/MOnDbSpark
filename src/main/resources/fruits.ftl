<html>
<head><title>Hello ftl</title></head>
<body>
  <form action = "/favorite_fruits" method="POST">
      <p> What is your favorait fruit </p>
              <#list fruits as fruit>
                <p>
                  <input type = "radio"  name="fruit" value = "${fruit}">${fruit}</input>
                </p>
              </#list>
      <input type="submit" value="submit"/>
  </form>
</body>

</html>