import React, { useState } from "react";
import UseMessageAlerts from "../hooks/UseMessageAlerts";
import { useParams } from "react-router-dom";

const UserUpdate = () => {

    const [isProcessing, setIsProcessing] = useState(false);
    const { userId } = useParams();
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