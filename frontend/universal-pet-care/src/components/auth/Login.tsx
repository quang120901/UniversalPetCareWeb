import React, { use } from "react";

const Login = () => {
    const [credentials, setCredentials] = use({
        email: "",
        password: "",
    })

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setCredentials((prevState) => ({
            ...prevState,
            [name]: value,
        }))
    }
    return (
        <div>

        </div>
    )
}

export default Login;