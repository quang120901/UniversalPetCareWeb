import React, { useState } from "react";
import UseMessageAlerts from "../hooks/UseMessageAlerts";

const UserUpdate = () => {
    const [userData, setUserData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        userType: "",
        gender: "",
        phoneNumber: "",
        specialization: "",
    });
    const {
    successMessage,
    setSuccessMessage,
    errorMessage,
    setErrorMessage,
    showSuccessAlert,
    setShowSuccessAlert,
    showErrorAlert,
    setShowErrorAlert,
  } = UseMessageAlerts();

    return(
        <div>

        </div>
    )
}

export default UserUpdate;