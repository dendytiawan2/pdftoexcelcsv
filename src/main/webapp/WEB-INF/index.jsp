<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>PDF To Excel</title>

    <style>
        .awalan {
            display: flex;
            justify-content: center;
        }

        .dalem {
            text-align: center;
        }
        #fileInput {
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="awalan">
        <div class="dalem">
            <h3>Import PDF</h3>
                <label for="importButton">Import File:</label>
                <input type="text" id="filename" name="filename"  readonly="true"/>
                <button id="importButton">Import</button>

                <input type="file" id="fileInput" name="fileInput" accept=".pdf" />
            <br><br>

            <label for="formatSelect">Select Output Format:</label>
            <select id="formatSelect">
                <option value="xlsx">Excel</option>
                <option value="csv">CSV</option>
            </select>
                <button id="submitButton">Submit</button>
        </div>
    </div>
</div>
<script>
    document
        .getElementById("importButton")
        .addEventListener("click", function () {
            document.getElementById("fileInput").click();
        });
    document
        .getElementById("fileInput")
        .addEventListener("change", function () {
            var filename = document.getElementById("fileInput").files[0].name;
            document.getElementById("filename").value = filename;
        });

    document.getElementById("submitButton").addEventListener("click", function () {
        var fileInput = document.getElementById("fileInput");
        var fileName = fileInput.files[0].name.split(".");
        var uploadUrl = "<c:url value="/upload"/>";
        if (fileInput.files.length > 0) {
            var formData = new FormData();
            formData.append("file", fileInput.files[0]);
            formData.append("format", formatSelect.value);
            formData.append("fileName", fileName[0]);
            fetch(uploadUrl, {
                method: "POST",
                body: formData
            })
                .then(response => response.blob())
                .then(blob => {
                    var url = window.URL.createObjectURL(blob);
                    var a = document.createElement('a');
                    a.href = url;
                    a.download = fileName[0]+"." + formatSelect.value;
                    document.body.appendChild(a);
                    a.click();
                    a.remove();
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        } else {
            alert("Please select a file first.");
        }
    });
</script>
</body>
</html>
