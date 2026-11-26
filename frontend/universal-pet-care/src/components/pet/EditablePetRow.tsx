import React, { useState } from "react";

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
        <div>

        </div>
    )
}

export default EditablePetRow;