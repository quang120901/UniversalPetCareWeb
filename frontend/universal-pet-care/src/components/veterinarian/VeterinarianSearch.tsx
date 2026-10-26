import React, { use, useState } from "react";
import format from "date-fns";
import UseMessageAlerts from "../hooks/UseMessageAlerts";

const VeterinarianSearch = ({onSearchResult}) => {
    const [searchQuery, setSearchQuery] = useState({
        date: null,
        time: null,
        specialization: "",
    });
    const [showDateTime, setShowDateTime] = useState(false);
    const {
        errorMessage,
        setErrorMessage,
        showErrorAlert,
        setShowErrorAlert } = UseMessageAlerts();

    const handleInputChange = (e) => {
        setSearchQuery({
            ...searchQuery,
            [e.target.name]: e.target.value
        });
    };

    const handleDataChange = (date) => {
        setSearchQuery({
            ...searchQuery,
            date
        });
    };

    const handleTimeChange = (time) => {
        setSearchQuery({
            ...searchQuery,
            time,
        });
    };

    const handleDateTimeToggle = (e) => {
        const isChecked = e.target.checked
        setShowDateTime(isChecked)
        if (!isChecked) {
            setSearchQuery({
                ...searchQuery,
                date: null,
                time: null,
            })
        }
    }

    const handleSearch = async (e) => {
        e.preventDefault()
        let searchParams = { specialization : searchQuery.specialization }

        if(searchQuery.date) {
            const formattedDate = format(searchQuery.date, "yyyy-MM-dd")
            searchParams.date = formattedDate
        }

        if(searchQuery.time) {
            const formattedTime = format(searchQuery.time, "HH:mm")
            searchParams.time = formattedTime
        }

        try {
            const response = await findAvailableVaterinarians(searchParams)
            onSearchResult(response.data)
            setShowErrorAlert(false)
        } catch (error) {
            setErrorMessage(error.message)
            setShowErrorAlert(true)
        }
    }

    const handleClearSearch = () => {
        setSearchQuery({
            date: null,
            time: null,
            specialization : ""
        })
        setShowDateTime(false)
        onSearchResult([])
    }

    return (
        <div>

        </div>
    );
}

export default VeterinarianSearch;