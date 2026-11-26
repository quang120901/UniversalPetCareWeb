import React, { useState } from "react";
import { Form } from "react-bootstrap";

const EditablePetRow = ({ pet, index, onSave, onCancel }) => {
    const [editPet, setEditPet] = useState(pet);

    const handlePetChange = (e) => {
        const { name, value } = e.target;
        setEditPet((prevState) => ({
            ...prevState,
            [name]: value,
        }))
    }

    return (
        <tr>
            <td>
                <Form.Control
                    type="text"
                    name="name"
                    value={editPet.name}
                    onChange={handlePetChange}
                />
            </td>

            <td>
                <Form.Control
                    type="text"
                    name="type"
                    value={editPet.type}
                    onChange={handlePetChange}
                />
            </td>

            <td>
                <Form.Control
                    type="text"
                    name="age"
                    value={editPet.age}
                    onChange={handlePetChange}
                />
            </td>
        </tr>
    )
}

export default EditablePetRow;