import { TagDto } from '@app/core/dto';

export interface TaskDto {
  id: number;
  link: string;
  userId: number;
  projectId: number;

  name: string;
  description: string;

  tags: TagDto[];
  children: TaskDto[];

  updatedAt: string;
  createdAt: string;
}

export interface TaskCreateDto {
  projectId: number;
  title: string;
  description: string;
  tags: TagDto[];
}
