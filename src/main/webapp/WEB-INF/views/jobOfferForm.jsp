<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Job Offer Form</title>
    <style>
        @import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }

        body {
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 800px;
        }

        h2 {
            text-align: center;
            color: #023047;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            width: calc(50% - 20px);
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="email"],
        input[type="date"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="date"]:focus,
        input[type="number"]:focus {
            border-color: #023047;
        }

        button {
            background-color: #ffb703;
            color: white;
            border: none;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 20px;
            width: 100%;
        }

        button:hover {
            background-color: #faa307;
        }

        a {
            display: inline-block;
            text-align: center;
            padding: 10px;
            text-decoration: none;
            color: white;
            background-color: #023047;
            border-radius: 5px;
            margin-top: 10px;
            transition: background-color 0.3s;
            width: 100%;
            text-align: center;
        }

        a:hover {
            background-color: #035e8b;
        }

        @media (max-width: 768px) {
            .form-group {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>${jobOffer == null ? 'Create New Job Offer' : 'Edit Job Offer'}</h2>
    <form action="jobOffers?action=${jobOffer == null ? 'insert' : 'update'}" method="post">
        <input type="hidden" name="id" value="${jobOffer.id}" />

        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" name="title" id="title" value="${jobOffer.title}" required />
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" name="description" id="description" value="${jobOffer.description}" required />
        </div>

        <div class="form-group">
            <label for="requirement">Requirement:</label>
            <input type="text" name="requirement" id="requirement" value="${jobOffer.requirement}" required />
        </div>

        <div class="form-group">
            <label for="publicationDate">Publication Date:</label>
            <input type="date" name="publicationDate" id="publicationDate" value="${jobOffer.publicationDate}" required />
        </div>

        <div class="form-group">
            <label for="expiryDate">Expiry Date:</label>
            <input type="date" name="expiryDate" id="expiryDate" value="${jobOffer.expiryDate}" required />
        </div>

        <div class="form-group">
            <label for="status">Status:</label>
            <input type="text" name="status" id="status" value="${jobOffer.status}" required />
        </div>

        <button type="submit">Submit</button>
        <a href="jobOffers">Cancel</a>
    </form>
</div>
</body>
</html>
