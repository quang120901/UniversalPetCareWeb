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

    const handleShowDeleteModal = (petId) => {
        setPetToDelete(petId);
        setShowDeleteModal(true);
    }

    const handleDeletePet = async () => {
        if (petToDelete) {
            try {
                const response = await deletePet(petToDelete);
                setSuccessMessage(response.message);
                setShowDeleteModal(false);
                setShowSuccessAlert(true);
                onPetsUpdate(appointmentId);
            } catch (error) {
                setErrorMessage(error.message);
                setShowErrorAlert(true);
            }
        }
    }

    const handleSavePetUpdate = async (petId, updatedPet) => {
        try {
            const response = await updatePet(petId, updatedPet);
            setSuccessMessage(response.message);
            setEditModeId(null);
            setShowSuccessAlert(true);
        } catch (error) {
            setErrorMessage(error.message);
            setShowErrorAlert(true);
        }
    }

    return (
        <div>

        </div>
    )
}

export default PetsTable;