<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Uploaded Photo</title>
</head>
<body>
    <h1>Uploaded Photo Preview</h1>

    <c:if test="${not empty path}">
        <img src="${path}" alt="Uploaded Photo" style="max-width: 500px; max-height: 500px;" />
        <p>Image successfully uploaded at: <strong>${path}</strong></p>
    </c:if>

    <c:if test="${empty path}">
        <p>No image uploaded.</p>
    </c:if>

    <br>
    <a href="image.jsp">Upload Another Photo</a>
</body>
</html>