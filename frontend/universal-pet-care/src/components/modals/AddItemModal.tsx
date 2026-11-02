import React, { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";

const AddItemModal = ({show, handleClose, handleSave, itemLabel}) => {
    const [itemValue, setItemValue] = useState("");

    const handleSaveItem = () => {
        handleSave(itemValue)
        setItemValue("")
        handleClose()
    }

    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Add New {itemLabel}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group>
                        <Form.Label>{itemLabel} Name</Form.Label>
                        <Form.Control 
                            type="text"
                            placeholder={`Enter ${itemLabel.toLowerCase()} Name`}
                            value={itemValue}
                            onChange={(e) => setItemValue(e.target.value)}
                        >
                        </Form.Control>
                    </Form.Group>
                </Form>

                <Modal.Footer>
                    <Button variant="secondary" onClick={handleSaveItem}>
                        Add
                    </Button>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal.Body>
        </Modal>
    )
}

export default AddItemModal;