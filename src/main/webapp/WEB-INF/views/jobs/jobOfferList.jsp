<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Job Offers</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }

        h2 {
            text-align: center;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .card {
            background: #e5e5e5;
            border: 1px solid #003049;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin: 15px;
            padding: 20px;
            width: 300px;
            transition: transform 0.3s;
        }
        .card h3 {
            margin: 0;
            font-size: 1.5em;
            color: #000000;
        }

        .card p {
            color: black;
        }

        .button {
            background-color: #c1121f;
            color: white;
            border: none;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 1em;
            margin-top: 10px;
            border-radius: 5px;
            cursor: pointer;
        }

        .create-job-offer {
            display: inline-block;
            text-align: center;
            margin: 20px auto;
            text-decoration: none;
            color: white;
            background-color: #fca311;
            padding: 10px 15px;
            border-radius: 5px;
            width: 200px;
        }

    </style>
</head>
<body>
<h2>List of Job Offers</h2>
<a href="jobOffers?action=new" class="create-job-offer">Create New Job Offer</a>
<div class="container">
    <c:forEach var="jobOffer" items="${jobOffers}">
        <div class="card">
            <h3>${jobOffer.title}</h3>
            <p><strong>Description:</strong> ${jobOffer.description}</p>
            <p><strong>Requirement:</strong> ${jobOffer.requirement}</p>
            <p><strong>Publication Date:</strong> ${jobOffer.publicationDate}</p>
            <p><strong>Expiry Date:</strong> ${jobOffer.expiryDate}</p>
            <p><strong>Status:</strong> ${jobOffer.status}</p>
            <a href="applyJob?id=${jobOffer.id}" class="button">Apply</a>
            <div>
                <a href="jobOffers?action=edit&id=${jobOffer.id}">Edit</a>
                <a href="jobOffers?action=delete&id=${jobOffer.id}" onclick="return confirm('Are you sure?')">Delete</a>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
