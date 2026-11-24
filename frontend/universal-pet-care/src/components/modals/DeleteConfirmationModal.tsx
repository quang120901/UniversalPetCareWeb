import React from "react";
import { Modal } from "react-bootstrap";

const DeleteConfirmationModal = ({
    show,
    onHide,
    onConfirm,
    itemToDelete,
    loading,
}) => {
    return (
        <Modal>
            <Modal.Header><Modal.Title>Delete Confirmation</Modal.Title></Modal.Header>
            <Modal.Body>Are you sure you want to delete {itemToDelete}? This action cannot be 
                undone.
            </Modal.Body>
        </Modal>
    )
}

export default DeleteConfirmationModal