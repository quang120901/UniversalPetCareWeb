import { api } from "../utils/api";

export async function updateUserPhoto(photoId, photoData) {
    try {
        const response = await api.put(`/photos/photo/${photoId}/update`, photoData, {
            headers: {
                "Content-Type": "application/octet-stream",
            }
        })
        return response.data;
    } catch (error) {
        throw error;
    }
}