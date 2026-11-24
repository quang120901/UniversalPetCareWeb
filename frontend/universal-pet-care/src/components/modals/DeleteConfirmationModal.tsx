import React from "react";
import { Button, Modal } from "react-bootstrap";
import ProcessSpinner from "../common/ProcessSpinner";

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

            <Modal.Footer>
                <Button variant="secondary" onClick={onHide}>
                    Cancel
                </Button>

                <ProcessSpinner message="Deleting..." />

                <Button variant="danger" onClick={onConfirm}>
                    Delete
                </Button>
            </Modal.Footer>
        </Modal>
    )
}

export default DeleteConfirmationModal