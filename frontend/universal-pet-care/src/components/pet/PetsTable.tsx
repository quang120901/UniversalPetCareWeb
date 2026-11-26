import React, { useState } from "react";
import UseMessageAlerts from "../hooks/UseMessageAlerts";

const PetsTable = ({ pets, appointmentId, onPetsUpdate, isEditable }) => {
    const [editModeId, setEditModeId] = useState(null);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [petToDelete, setPetToDelete] = useState(null);

    const {
    successMessage,
    setSuccessMessage,
    showSuccessAlert,
    setShowSuccessAlert,
    errorMessage,
    setErrorMessage,
    showErrorAlert,
    setShowErrorAlert,
  } = UseMessageAlerts();

    const handleEditClick = (petId) => {
        setEditModeId(petId);
    };

    const handleCancel = () => {
        setEditModeId(null);
    }

    return (
        <div>

        </div>
    )
}

export default PetsTable;